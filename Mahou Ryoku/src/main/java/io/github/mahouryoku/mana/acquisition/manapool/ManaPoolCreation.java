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
    public void SetManaPool(boolean Unlocked) {
        this.getManaPool = Unlocked;
    }
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag NBT = new CompoundTag();
        NBT.putBoolean("HasManaPool", this.getManaPool);
        return NBT;
    }
    @Override
    public void deserializeNBT(CompoundTag NBT) {
        if (NBT.contains("HasManaPool")) {
            this.getManaPool = NBT.getBoolean("HasManaPool");
        }
    }
}
