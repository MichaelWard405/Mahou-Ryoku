package xyz.hiveforge.mahouryoku.race_system.player_races;

import net.minecraft.server.level.ServerPlayer;
import xyz.hiveforge.mahouryoku.race_system.PlayerRace;

public class Human_Race implements PlayerRace {
    //==============================
    // Sets Race ID and DisplayName
    //==============================
    @Override public String GetID() {return "human";}
    @Override public String GetDisplayName() {return "Human";}

    //==============================
    //          Race Logic
    //==============================
    @Override
    public void OnTick(ServerPlayer Player) {}
}
