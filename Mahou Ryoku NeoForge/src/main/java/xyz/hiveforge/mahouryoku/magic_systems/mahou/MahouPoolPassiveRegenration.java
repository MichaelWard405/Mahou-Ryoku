package xyz.hiveforge.mahouryoku.magic_systems.mahou;


import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import xyz.hiveforge.mahouryoku.Registries.MagicRegistries;

@EventBusSubscriber(modid = "mahouryoku")
public class MahouPoolPassiveRegenration {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            MahouPool data = player.getData(MagicRegistries.MAHOU_POOL.get());
            if (data.GetMahouPool){
                data.RegenerateMahouPool(.03f);

            }
            if(player.tickCount % 20 == 0) {
                player.setData(MagicRegistries.MAHOU_POOL.get(), data);
            }
        }
    }

}
