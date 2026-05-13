package xyz.hiveforge.mahouryoku.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class Mahou_Items extends Item {
    public static final String MODID = "mahouryoku";
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> RENDER_STATE =
            COMPONENTS.registerComponentType("render_state", builder -> builder.persistent(Codec.FLOAT).networkSynchronized(ByteBufCodecs.FLOAT));

    public Mahou_Items(Properties properties) {
        super(properties);
    }


    public record MahouData(int mana, int affinity, int eccentricity, float state) {
        public static final Codec<MahouData> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.fieldOf("mana").forGetter(MahouData::mana),
                        Codec.INT.fieldOf("affinity").forGetter(MahouData::affinity),
                        Codec.INT.fieldOf("eccentricity").forGetter(MahouData::eccentricity),
                        Codec.FLOAT.fieldOf("state").forGetter(MahouData::state)
                ).apply(instance, MahouData::new)
        );
    }
    public static final StreamCodec<RegistryFriendlyByteBuf, MahouData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, MahouData::mana,
            ByteBufCodecs.VAR_INT, MahouData::affinity,
            ByteBufCodecs.VAR_INT, MahouData::eccentricity,
            ByteBufCodecs.FLOAT, MahouData::state,
            MahouData::new
    );
    public static float state(int mana, int affinity, int eccentricity) {
        float tier;
        if (mana >= 1000) {
            tier = 2.0f;
        } else if (mana >= 500) {
            tier = 1.0f;
        } else if (mana >= 100) {
            tier = 0.0f;
        } else {
            tier = 0.0f;
        }
        return tier + (affinity / 10.0f);
    }
    public static void UpdateItemState(ItemStack itemStack, int mana, int affinity, int eccentricity) {
        float currentState = state(mana, affinity, eccentricity);
        itemStack.set(MAHOU_DATA.get(), new MahouData(mana, affinity, eccentricity, currentState));
        itemStack.set(RENDER_STATE.get(), currentState);
        itemStack.setPopTime(3);
    }


    public static final DeferredHolder<DataComponentType<?>, DataComponentType<MahouData>> MAHOU_DATA = COMPONENTS.registerComponentType("mahou_data", builder -> builder.persistent(MahouData.CODEC).networkSynchronized(STREAM_CODEC));
    public static final DeferredItem<Mahou_Items> GATHERING_MAHOU = ITEMS.registerItem("mahou",
            properties -> new Mahou_Items(properties.stacksTo(1).component(MAHOU_DATA.get(),new MahouData(0,0,0, state(0,0,0))).component(RENDER_STATE.get(), state(0,0,0))));


    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        COMPONENTS.register(modEventBus);
    }
}