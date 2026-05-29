package xyz.hiveforge.mahouryoku.race_system.player_races;

import net.minecraft.server.level.ServerPlayer;
import xyz.hiveforge.mahouryoku.race_system.PlayerRace;

public class Dwarf_Race implements PlayerRace {
    //==============================
    // Sets Race ID and DisplayName
    //==============================
    @Override public String GetID() {return "dwarf";}
    @Override public String GetDisplayName() {return "Dwarf";}

    //==============================
    //          Race Logic
    //==============================
    @Override
    public void OnTick(ServerPlayer Player) {}
}