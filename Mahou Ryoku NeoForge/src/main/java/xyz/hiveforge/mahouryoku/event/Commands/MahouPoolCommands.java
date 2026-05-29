package xyz.hiveforge.mahouryoku.event.Commands;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import xyz.hiveforge.mahouryoku.Registries.PlayerDataAttachment;
import xyz.hiveforge.mahouryoku.magic_systems.mahou.MahouPool;

@EventBusSubscriber(modid = "mahouryoku")
public class MahouPoolCommands {

    //===============================================
    //              Register Admin Commands
    //===============================================
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("mahou")

                        //======================================================
                        //                          Check
                        //Used to Check your Player stats (i.e: Mana, Affinity)
                        //======================================================
                        .then(Commands.literal("check")
                                .executes(context -> {
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    MahouPool data = player.getData(PlayerDataAttachment.MAHOU_POOL.get());

                                    //Constructs The Reply message supplying data within the users Capability Codec
                                    String status = data.GetMahouPool() ? "§aUnlocked" : "§cLocked";
                                    player.sendSystemMessage(Component.literal("§6--- Mahou Stats ---"));
                                    player.sendSystemMessage(Component.literal("Status: " + status));
                                    player.sendSystemMessage(Component.literal("Mana: §b" + data.MahouPoolValue() + "§f / §3" + data.MahouPoolSize()));

                                    //Uses Affinity Integer Value to Determine Affinity
                                    String Affinity = switch ((int) data.Affinity()) {
                                        case 1 -> "Inferno";
                                        default -> "Arcane Neutral";
                                    };
                                    player.sendSystemMessage(Component.literal("Affinity: §e" + Affinity));

                                    //Uses Eccentricity Integer Value to Determine Eccentricity
                                    String Eccentricity = switch ((int) data.Eccentricity()) {
                                        case 1 -> "Neutral";
                                        default -> "None";
                                    };
                                    player.sendSystemMessage(Component.literal("Eccentricity: §e" + Eccentricity));
                                    return 1;
                                }))

                        //====================================
                        //               Unlock
                        //      Used to Obtain Mahou Pool
                        //====================================
                        .then(Commands.literal("Unlock")
                                .executes(context -> {
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    MahouPool data = player.getData(PlayerDataAttachment.MAHOU_POOL.get());

                                    data.GetMahouPool = true;
                                    return 0;
                                }))

                        //==================================
                        //              Lock
                        //      Used to Lock Mahou Pool
                        //==================================
                        .then(Commands.literal("Lock")
                                .executes(context -> {
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    MahouPool data = player.getData(PlayerDataAttachment.MAHOU_POOL.get());

                                    data.GetMahouPool = false;
                                    data.MahouPool = 1f;
                                    return 0;
                                }))

                        //===================================
                        //              Spend
                        //      Used to Spend 10 Mahou
                        //===================================
                        .then(Commands.literal("Spend")
                                .executes(context -> {
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    MahouPool data = player.getData(PlayerDataAttachment.MAHOU_POOL.get());
                                    if (data.GetMahouPool || data.MahouPool >= 10){
                                        data.ConsumeFromMahouPool(10f);
                                    }
                                    return 0;
                                }))
        );
    }
}
