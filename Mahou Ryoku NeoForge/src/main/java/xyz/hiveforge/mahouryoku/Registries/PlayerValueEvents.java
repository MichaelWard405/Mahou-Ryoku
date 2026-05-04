package xyz.hiveforge.mahouryoku.Registries;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import xyz.hiveforge.mahouryoku.magic_systems.mahou.MahouPool;

public class PlayerValueEvents {
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            MahouPool oldData = event.getOriginal().getData(MagicRegistries.MAHOU_POOL);
            MahouPool newData = new MahouPool(
                    oldData.GetMahouPool,
                    1.0f,                  // Exception: Reset pool value on death
                    oldData.MahouPoolSize, // Keep size
                    oldData.Affinity,      // Keep affinity
                    oldData.Eccentricity   // Keep eccentricity
            );
            event.getEntity().setData(MagicRegistries.MAHOU_POOL, newData);
        }
    }
}

