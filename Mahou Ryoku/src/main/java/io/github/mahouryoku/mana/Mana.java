package io.github.mahouryoku.mana;

import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class Mana implements ManaInterface, INBTSerializable<FloatTag> {
    private float mana = 100.0f; // Default mana
    private final float maxMana = 100.0f;

    @Override
    public float getMana() { return mana; }

    @Override
    public void setMana(float mana) {
        this.mana = Math.max(0, Math.min(mana, maxMana));
    }

    @Override
    public void consume(float mana) {
        this.mana = Math.max(0, this.mana - mana);
    }

    @Override
    public void regenerate() {
        if (this.mana < maxMana) this.mana += 1.0f;
    }

    @Override
    public FloatTag serializeNBT() {
        return FloatTag.valueOf(this.mana);
    }

    @Override
    public void deserializeNBT(FloatTag nbt) {
        this.mana = nbt.getAsFloat();
    }
}