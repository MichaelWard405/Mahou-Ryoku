package io.github.mahouryoku.mana.acquisition.manapool;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class ManaPoolCreation implements ManaPoolCreationInterface, INBTSerializable<CompoundTag> {
    public boolean getManaPool = false;

    @Override
    public boolean GetManaPool() {
        return getManaPool;
    }
    @Override
    public void SetManaPool(boolean unlocked) {
        this.getManaPool = unlocked;
    }
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("HasManaPool", getManaPool);
        return tag;
    }
    @Override
    public void deserializeNBT(CompoundTag tag) {
        if (tag.contains("HasManaPool")) {
            this.getManaPool = tag.getBoolean("HasManaPool");
        }
    }
}
