package io.github.mahouryoku.item.crystalline_mahou;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class ModItems {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, "mahouryoku");

    public static final RegistryObject<Item> CRYSTALLINE_MAHOU = ITEM.register("crystalline_mahou", () -> new Crystalline_Mahou_Use(new Item.Properties().stacksTo(12)));



    public static void register(IEventBus eventBus) {ITEM.register(eventBus);}
}
