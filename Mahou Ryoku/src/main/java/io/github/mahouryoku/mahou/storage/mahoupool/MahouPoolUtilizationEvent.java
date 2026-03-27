package io.github.mahouryoku.mahou.storage.mahoupool;

import net.minecraft.world.entity.player.Player;
import static io.github.mahouryoku.mahou.storage.mahoupool.MahouPoolProvider.MAHOUPOOL_CAPABILITY;

public class MahouPoolUtilizationEvent {
    public static boolean MahouPoolUtilization(Player player, int MahouCost) {
        return player.getCapability(MAHOUPOOL_CAPABILITY).map(MahouPool -> {
        return MahouPool.GetManhouPool() && MahouPool.MahouPoolValue() >= MahouCost;})
                .orElse(false);}

}
