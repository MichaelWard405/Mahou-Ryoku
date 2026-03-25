package io.github.mahouryoku.event;

import io.github.mahouryoku.mana.acquisition.manapool.ManaPoolCreationInterface;
import io.github.mahouryoku.mana.acquisition.manapool.ManaPoolCreationProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "mahouryoku")
class ManaPoolCreationCapabilityEvent {

    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) { event.register(ManaPoolCreationInterface.class);}

    @SubscribeEvent
    public static void onAttchCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(ResourceLocation.fromNamespaceAndPath("mahouryoku", "manapoolcreation"), new ManaPoolCreationProvider());
        }
    }
}
