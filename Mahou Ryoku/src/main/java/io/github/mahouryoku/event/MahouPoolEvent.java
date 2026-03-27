package io.github.mahouryoku.event;

import io.github.mahouryoku.mahou.storage.mahoupool.MahouPoolInterFace;
import io.github.mahouryoku.mahou.storage.mahoupool.MahouPoolProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "mahouryoku")
public class MahouPoolEvent {
    @SubscribeEvent
    public static void RegisterCaps(RegisterCapabilitiesEvent event) { event.register(MahouPoolInterFace.class);}

    @SubscribeEvent
    public static void OnAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(ResourceLocation.fromNamespaceAndPath("mahouryoku", "mahoupool"), new MahouPoolProvider());
        }
    }

}
