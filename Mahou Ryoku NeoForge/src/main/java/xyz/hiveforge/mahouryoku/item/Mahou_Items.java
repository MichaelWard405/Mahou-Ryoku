package xyz.hiveforge.mahouryoku.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Mahou_Items extends Item {
    public static final String MODID = "mahouryoku";
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);
    public Mahou_Items(Properties properties) {
        super(properties);
    }

    public record MahouData(int mana, int affinity, int eccentricity) {
        public static final Codec<MahouData> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.fieldOf("mana").forGetter(MahouData::mana),
                        Codec.INT.fieldOf("affinity").forGetter(MahouData::affinity),
                        Codec.INT.fieldOf("eccentricity").forGetter(MahouData::eccentricity)
                ).apply(instance, MahouData::new)
        );
    }

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<MahouData>> MAHOU_DATA = COMPONENTS.registerComponentType("mahou_data", builder -> builder.persistent(MahouData.CODEC));
    public static final DeferredItem<Mahou_Items> GATHERING_MAHOU = ITEMS.registerItem("mahou",
            properties -> new Mahou_Items(properties.stacksTo(1).component(MAHOU_DATA.get(), new MahouData(0, 0, 0))));


    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        COMPONENTS.register(modEventBus);
    }
}