package xyz.hiveforge.mahouryoku;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterItemModelsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import xyz.hiveforge.mahouryoku.item.Mahou_Items;

@Mod(value = mahouryoku.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = mahouryoku.MODID, value = Dist.CLIENT)
public class mahouryokuClient {
    public mahouryokuClient(ModContainer container) {
    }
    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {

    }


}
