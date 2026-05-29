package xyz.hiveforge.mahouryoku.race_system;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerPlayer;
import xyz.hiveforge.mahouryoku.Registries.RaceRegisteries;

import java.util.ArrayList;
import java.util.List;

public class PlayerRaceData {
    private String CurrentRace; // Holds Player's Current Race
    private List<String> UnlockedRaces; // Holds Player's Unlocked Races

    //========================
    //    Stores Race Info
    //========================
    public PlayerRaceData(String CurrentRace, List<String> UnlockedRaces) {
        this.CurrentRace = CurrentRace;
        this.UnlockedRaces = new ArrayList<>(UnlockedRaces);
    }
    public PlayerRaceData(){this(RaceRegisteries.HUMAN.GetID(), List.of(RaceRegisteries.HUMAN.GetID()));}

    //============================
    //   Builds Race Data Codec
    //============================
    public static final MapCodec<PlayerRaceData> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("CurrentRace").forGetter(PlayerRaceData :: GetCurrentRaceID),
            Codec.STRING.listOf().fieldOf("UnlockedRaces").forGetter(PlayerRaceData :: GetUnlockedRaces)
    ).apply(instance, PlayerRaceData :: new));

    //===============================
    //     Getters For Race Data
    //===============================
    public String GetCurrentRaceID() { return this.CurrentRace;}
    public List<String> GetUnlockedRaces() {return this.UnlockedRaces;}
    public PlayerRace GetRace() {return RaceRegisteries.Get(this.CurrentRace);}


    //==============================
    //     Setters For Race Data
    //==============================
    public void SetRace(PlayerRace Race, ServerPlayer Player) {
        this.GetRace().OnRemoved(Player);
        this.CurrentRace = Race.GetID();
        Race.OnSelected(Player);
    }
    public void SetUnlockedRaces(List<String> UnlockedRaces) {
        this.UnlockedRaces = new ArrayList<>(UnlockedRaces);
    }


}
