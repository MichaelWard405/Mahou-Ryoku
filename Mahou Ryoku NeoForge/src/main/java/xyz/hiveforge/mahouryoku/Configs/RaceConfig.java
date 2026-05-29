package xyz.hiveforge.mahouryoku.Configs;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class RaceConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> DISABLED_RACES;


    //=================================
    //        Builds Race Config
    //=================================
    static {
        BUILDER.push("Race Management Options");
        DISABLED_RACES = BUILDER
                .comment("Specify race ID's That cannot be used on the server/ and or client")
                .defineListAllowEmpty(List.of("DisabledRaces"), List::of, s -> s instanceof String);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static boolean IsRaceEnabled(String RaceID) {return !DISABLED_RACES.get().contains(RaceID);}

}
