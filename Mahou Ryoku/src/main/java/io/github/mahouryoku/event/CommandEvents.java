package io.github.mahouryoku.event;

import io.github.mahouryoku.mana.storage.mahoupool.MahouPool;
import io.github.mahouryoku.mana.storage.mahoupool.MahouPoolProvider;
import io.github.mahouryoku.mana.storage.mahoupool.MahouPoolUtilizationEvent;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "mahouryoku", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommandEvents {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        int MahouCost = 0;
        event.getDispatcher().register(Commands.literal("CheckMana" )
                .executes(context -> {
                    if (context.getSource().getEntity() instanceof ServerPlayer player) {
                        if (MahouPoolUtilizationEvent.MahouPoolUtilization(player, MahouCost)) {
                            player.getCapability(MahouPoolProvider.MAHOUPOOL_CAPABILITY).ifPresent(mahou -> {
                                int CurrentMahou = (int) mahou.MahouPoolValue();
                                player.sendSystemMessage(Component.literal("Current Mana: " + CurrentMahou));
                            });
                            return 1;
                        }
                    }
                    return 0;
                }));
        event.getDispatcher().register(Commands.literal("GiveMahouPool")
                .executes(context -> {
                    if (context.getSource().getEntity() instanceof ServerPlayer player) {
                        if (MahouPoolUtilizationEvent.MahouPoolUtilization(player, MahouCost)) {
                            player.getCapability(MahouPoolProvider.MAHOUPOOL_CAPABILITY).ifPresent(mahou -> {
                                player.sendSystemMessage(Component.literal("You Already have a MahouPool"));
                            });
                            return 1;
                        }else {
                            player.getCapability(MahouPoolProvider.MAHOUPOOL_CAPABILITY).ifPresent(mahou -> {
                                mahou.SetMahouPool(true);
                            });
                            return 1;
                        }
                    }
                    return 0;
                }));
    }
}