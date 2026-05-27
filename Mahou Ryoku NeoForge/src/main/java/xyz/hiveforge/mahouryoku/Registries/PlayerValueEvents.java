package xyz.hiveforge.mahouryoku.Registries;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import xyz.hiveforge.mahouryoku.magic_systems.mahou.MahouPool;

public class PlayerValueEvents {

    //=====================================================================
    //                    Player Data Transfer On Death
    //Transfers the Players Data On Death Without Mahou or CoolDown Present
    //=====================================================================
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            MahouPool oldData = event.getOriginal().getData(MagicRegistries.MAHOU_POOL);
            MahouPool newData = new MahouPool(
                    oldData.GetMahouPool,
                    1.0f,
                    oldData.MahouPoolSize,
                    oldData.Affinity,
                    oldData.Eccentricity
            );
            event.getEntity().setData(MagicRegistries.MAHOU_POOL, newData);
        }
    }
}

