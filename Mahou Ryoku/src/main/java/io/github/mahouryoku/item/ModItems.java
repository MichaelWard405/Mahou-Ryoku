package io.github.mahouryoku.item;

import io.github.mahouryoku.MahouRyoku;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, "mahouryoku");

    public static final RegistryObject<Item> CRYSTALLINE_MAHOU = ITEM.register("crystalline_mahou", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEM.register(eventBus);
    }

}
