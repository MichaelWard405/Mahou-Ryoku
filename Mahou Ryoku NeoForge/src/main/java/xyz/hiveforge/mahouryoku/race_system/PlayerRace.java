package xyz.hiveforge.mahouryoku.race_system;

import net.minecraft.server.level.ServerPlayer;

public interface PlayerRace {
    String GetID(); //Stores Race ID
    String GetDisplayName(); //Stores Race Display Name

    void OnTick(ServerPlayer Player);

    default void OnSelected(ServerPlayer Player) {} // Gets Race ID
    default void OnRemoved(ServerPlayer Player) {} // Gets Race Display Name
}
