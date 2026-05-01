package xyz.hiveforge.mahouryoku.event.Commands;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import xyz.hiveforge.mahouryoku.Registries.MagicRegistries;
import xyz.hiveforge.mahouryoku.magic_systems.mahou.MahouPool;

@EventBusSubscriber(modid = "mahouryoku")
public class MahouPoolCommands {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("mahou")
                        .then(Commands.literal("check")
                                .executes(context -> {
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    MahouPool data = player.getData(MagicRegistries.MAHOU_POOL.get());

                                    String status = data.GetManhouPool() ? "§aUnlocked" : "§cLocked";
                                    player.sendSystemMessage(Component.literal("§6--- Mahou Stats ---"));
                                    player.sendSystemMessage(Component.literal("Status: " + status));
                                    player.sendSystemMessage(Component.literal("Mana: §b" + data.MahouPoolValue() + "§f / §3" + data.MahouPoolSize()));

                                    return 1;
                                }))
        );
    }
}
