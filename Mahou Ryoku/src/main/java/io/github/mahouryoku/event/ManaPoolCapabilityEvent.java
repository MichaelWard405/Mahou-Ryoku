package io.github.mahouryoku.event;


import io.github.mahouryoku.mana.storage.manapool.ManaPoolCapabilitesProvider;
import io.github.mahouryoku.mana.storage.manapool.ManaPoolInterface;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

    @Mod.EventBusSubscriber(modid = "mahouryoku")
    public class ManaPoolCapabilityEvent {
        @SubscribeEvent
        public static void registerCaps(RegisterCapabilitiesEvent event) {
            event.register(ManaPoolInterface.class);
        }
        @SubscribeEvent
        public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                event.addCapability(ResourceLocation.fromNamespaceAndPath("mahouryoku", "manapool"), new ManaPoolCapabilitesProvider());
            }
        }
    }