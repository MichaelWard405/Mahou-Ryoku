package xyz.hiveforge.mahouryoku.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomModelData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.Nullable;

import java.util.List;



public class Mahou_Items extends Item {
    //set Mod ID and Registries for the Custom Mahou Items and there Stored Data (NBT)
    public static final String MODID = "mahouryoku";
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);
    public Mahou_Items(Properties properties) {
        super(properties);
    }


    //Gets the Mahou Item and Updates it Values to Remain Synced
    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        super.inventoryTick(itemStack, level, owner, slot);
        var data = itemStack.get(MAHOU_DATA.get());
        if (data != null) {
            float stateValue = state(data.mahou, data.affinity, data.eccentricity);

            itemStack.set(DataComponents.CUSTOM_MODEL_DATA,
                    new CustomModelData(List.of(stateValue), List.of(), List.of(), List.of()));
            itemStack.setPopTime(1);
        }

    }

    //===============================
    //       Mahou Item Data
    //  The Data Stored Within the
    //Mahou Item to determine Its Use
    //===============================
    public record MahouData(int mahou, int affinity, int eccentricity, float state) {
        public static final Codec<MahouData> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.fieldOf("mahou").forGetter(MahouData::mahou),
                        Codec.INT.fieldOf("affinity").forGetter(MahouData::affinity),
                        Codec.INT.fieldOf("eccentricity").forGetter(MahouData::eccentricity),
                        Codec.FLOAT.fieldOf("state").forGetter(MahouData::state)
                ).apply(instance, MahouData::new)
        );
    }

    //========================================
    //          Mahou Item Data Stream
    //Stream the Item Data to Sync to Clients
    //========================================
    public static final StreamCodec<RegistryFriendlyByteBuf, MahouData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, MahouData::mahou,
            ByteBufCodecs.VAR_INT, MahouData::affinity,
            ByteBufCodecs.VAR_INT, MahouData::eccentricity,
            ByteBufCodecs.FLOAT, MahouData::state,
            MahouData::new
    );

    //======================================
    //           Mahou Item State
    // Uses the Mahou to determine its Tier
    //======================================
    public static float state(int mahou, int affinity, int eccentricity) {
        float tier;
        float affinityoffset = affinity /10f ;
        if (mahou >= 1000) {
            tier = 2.0f;
        } else if (mahou >= 100) {
            tier = 1.0f;
        } else if (mahou >= 0) {
            tier = 0.0f;
        } else {
            tier = 0.0f;
        }
        return tier + affinityoffset;
    }


    //Register the Data and Item With the Correct Item Properties and Attach Mahou Item Data
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<MahouData>> MAHOU_DATA = COMPONENTS.registerComponentType("mahou_data", builder -> builder.persistent(MahouData.CODEC).networkSynchronized(STREAM_CODEC));
    public static final DeferredItem<Mahou_Items> MAHOU = ITEMS.registerItem("mahou",
            properties -> new Mahou_Items(properties.stacksTo(1).component(MAHOU_DATA.get(),new MahouData(0,0,0, state(0,0,0)))));

    //Register the Item and Item Data to the Mod Bus
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        COMPONENTS.register(modEventBus);
    }
}