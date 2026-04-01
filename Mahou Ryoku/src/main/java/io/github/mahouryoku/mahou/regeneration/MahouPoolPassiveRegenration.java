package io.github.mahouryoku.mahou.regeneration;


import io.github.mahouryoku.mahou.storage.mahoupool.MahouPoolProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "mahouryoku", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MahouPoolPassiveRegenration {
    @SubscribeEvent
    public static void OnPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            if (event.side.isServer()) {
                player.getCapability(MahouPoolProvider.MAHOUPOOL_CAPABILITY).ifPresent(Mahou -> {
                    if (player.level().getGameTime() % 20 == 0) {
                        if (Mahou.MahouPoolValue() < Mahou.MahouPoolSize()) {
                            Mahou.RegenerateMahouPool(990.3f); //Chane back set high for testin
                            System.out.println("MAHOU VALUE: " + Mahou.MahouPoolValue());
                        }}});}}}}