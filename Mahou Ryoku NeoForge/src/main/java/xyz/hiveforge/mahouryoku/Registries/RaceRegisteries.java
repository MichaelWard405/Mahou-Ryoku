package xyz.hiveforge.mahouryoku.Registries;

import xyz.hiveforge.mahouryoku.race_system.PlayerRace;
import xyz.hiveforge.mahouryoku.race_system.player_races.Dwarf_Race;
import xyz.hiveforge.mahouryoku.race_system.player_races.Human_Race;
import xyz.hiveforge.mahouryoku.race_system.player_races.Vampire_Race;

import java.util.HashMap;
import java.util.Map;


public class RaceRegisteries {
    private static final Map<String, PlayerRace> REGISTERED_RACES = new HashMap<>();

    //=====================
    //  Register Each Race
    //=====================
    public static final PlayerRace HUMAN = Register(new Human_Race());
    public static final PlayerRace VAMPIRE = Register(new Vampire_Race());
    public static final PlayerRace DWARF = Register(new Dwarf_Race());

    //==================================
    //  Register Races to Respective ID
    //==================================
    private static PlayerRace Register(PlayerRace race) {
        REGISTERED_RACES.put(race.GetID(), race);
        return race;
    }

    //===============================
    //  Get Players Registered Race
    //===============================
    public static PlayerRace Get(String ID) {
        return REGISTERED_RACES.getOrDefault(ID, HUMAN);
    }


}
