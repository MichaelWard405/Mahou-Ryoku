package io.github.mahouryoku.mana;

import net.minecraft.nbt.FloatTag;
import net.minecraftforge.common.util.INBTSerializable;

public class ManaPool implements ManaPoolInterface, INBTSerializable<FloatTag> {
    private float manaPool = 100.0f; // Default mana
    private final float maxManaPool = 100.0f;

    @Override
    public float getManaPool() { return manaPool; }

    @Override
    public void setManaPool(float mana) {
        this.manaPool = Math.max(0, Math.min(mana, maxManaPool));
    }

    @Override
    public void consumeManaPool(float mana) {
        this.manaPool = Math.max(0, this.manaPool - mana);
    }

    @Override
    public void regenerateManaPool() {
        if (this.manaPool < maxManaPool) this.manaPool += 1.0f;
    }

    @Override
    public FloatTag serializeNBT() {
        return FloatTag.valueOf(this.manaPool);
    }

    @Override
    public void deserializeNBT(FloatTag nbt) {
        this.manaPool = nbt.getAsFloat();
    }
}