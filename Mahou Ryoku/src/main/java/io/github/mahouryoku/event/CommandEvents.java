package io.github.mahouryoku.event;

import io.github.mahouryoku.mana.acquisition.manapool.ManaPoolCreationProvider;
import io.github.mahouryoku.mana.storage.manapool.ManaPoolCapabilitesProvider;
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
                        player.getCapability(ManaPoolCapabilitesProvider.MANAPOOL_CAPABILITY).ifPresent(mana -> {
                            // 2. Format the message
                            int currentMana = (int) mana.getManaPool();
                            // 3. Send message to chat
                            player.sendSystemMessage(Component.literal("Current Mana: " + currentMana));
                        });
                        return 1; // Success
                    }
                    return 0; // Failure
                }));
        event.getDispatcher().register(Commands.literal("spendmana")
                .executes(context -> {
                    if (context.getSource().getEntity() instanceof ServerPlayer player) {
                        player.getCapability(ManaPoolCapabilitesProvider.MANAPOOL_CAPABILITY).ifPresent(mana -> {
                            if (mana.getManaPool() >= 10) {
                                mana.consumeManaPool(10);
                            }});
                        return 1;
                    }
                    return 0;
                }));
        event.getDispatcher().register(Commands.literal("manapool")
                .executes(context -> {
                    if (context.getSource().getEntity() instanceof ServerPlayer player) {
                        player.getCapability(ManaPoolCreationProvider.MANAPOOLCREATION_CAPABILITY).ifPresent(manapool -> {
                            if (manapool.GetManaPool() == true) {
                                player.sendSystemMessage(Component.literal("You Have a ManaPool"));
                            } else if (manapool.GetManaPool() == false) {
                                player.sendSystemMessage(Component.literal("You don't have a ManaPool Yet"));
                            }
                        });
                        return 1;
                    }
                    return 0;
                }));
    }
}