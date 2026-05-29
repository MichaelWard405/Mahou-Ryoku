package xyz.hiveforge.mahouryoku.race_system.player_races;

import net.minecraft.server.level.ServerPlayer;
import xyz.hiveforge.mahouryoku.race_system.PlayerRace;

public class Vampire_Race implements PlayerRace {
    //==============================
    // Sets Race ID and DisplayName
    //==============================
    @Override public String GetID() {return "vampire";}
    @Override public String GetDisplayName() {return "Vampire";}

    //==============================
    //          Race Logic
    //==============================
    @Override
    public void OnTick(ServerPlayer Player) {}
}