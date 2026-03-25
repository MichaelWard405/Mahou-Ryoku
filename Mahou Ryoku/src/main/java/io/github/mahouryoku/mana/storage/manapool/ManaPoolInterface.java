package io.github.mahouryoku.mana.storage.manapool;

import net.minecraft.server.level.ServerPlayer;

public interface ManaPoolInterface {
    float getManaPool();
    void setManaPool(float mana);
    void consumeManaPool(float mana);
    void regenerateManaPool();

}