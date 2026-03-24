package io.github.mahouryoku.event;

import io.github.mahouryoku.mana.ManaCapabilitesProvider;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "mahouryoku", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommandEvents {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("checkmana")
                .executes(context -> {
                    // Ensure this is a player
                    if (context.getSource().getEntity() instanceof ServerPlayer player) {
                        // 1. Get the capability
                        player.getCapability(ManaCapabilitesProvider.MANA_CAPABILITY).ifPresent(mana -> {
                            // 2. Format the message
                            int currentMana = (int) mana.getMana();
                            // 3. Send message to chat
                            player.sendSystemMessage(Component.literal("Current Mana: " + currentMana));
                        });
                        return 1; // Success
                    }
                    return 0; // Failure
                })
        );
    }
}