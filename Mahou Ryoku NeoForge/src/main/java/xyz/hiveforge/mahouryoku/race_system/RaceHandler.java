package xyz.hiveforge.mahouryoku.race_system;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.profiling.jfr.stats.CpuLoadStat;
import net.minecraft.world.entity.npc.villager.Villager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import xyz.hiveforge.mahouryoku.Configs.RaceConfig;
import xyz.hiveforge.mahouryoku.Registries.PlayerDataAttachment;
import xyz.hiveforge.mahouryoku.Registries.RaceRegisteries;
import xyz.hiveforge.mahouryoku.WebServerFunc.WebStatManager;

@EventBusSubscriber(modid = "mahouryoku")
public class RaceHandler {

    //========================
    //   Player Login Logic
    //========================
    @SubscribeEvent
    public static void OnPlayerLogin(PlayerEvent.PlayerLoggedInEvent Event) {
        if (Event.getEntity() instanceof ServerPlayer Player) {
            WebStatManager.FetchPlayerStatsAsync(Player);
        }
    }

    //========================
    //      Race Unlocks
    //========================
        //===================
        //      Vampire
        //===================
    @SubscribeEvent
    public static void OnVillagerKill(LivingDeathEvent Event) {
        if (Event.getEntity() instanceof Villager && Event.getSource().getEntity() instanceof ServerPlayer Player) {
            PlayerRaceData Data = Player.getData(PlayerDataAttachment.PLAYER_RACE);

            if (!Data.GetUnlockedRaces().contains(RaceRegisteries.VAMPIRE.GetID())) {
                WebStatManager.SendUnlockRequestAsync(Player, RaceRegisteries.VAMPIRE.GetID());
            }
        }
    }


    //=============================
    //      Validation Block
    //=============================
    @SubscribeEvent
    public static void OnPlayerTick(PlayerTickEvent.Post Event) {
        if (Event.getEntity() instanceof ServerPlayer Player) {
            PlayerRaceData Data = Player.getData(PlayerDataAttachment.PLAYER_RACE);
            String RaceID = Data.GetCurrentRaceID();
            if (!RaceConfig.IsRaceEnabled(RaceID)) return;

            if (!RaceRegisteries.HUMAN.GetID().equals(RaceID) && !Data.GetUnlockedRaces().contains(RaceID)) {
                Data.SetRace(RaceRegisteries.HUMAN, Player);
                return;
            }
            Data.GetRace().OnTick(Player);
        }
    }
}
