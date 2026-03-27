package io.github.mahouryoku.mana.storage.mahoupool;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class MahouPool implements MahouPoolInterFace, INBTSerializable<CompoundTag> {
    public boolean GetManhouPool = false;
    public float MahouPool = 0f;
    public float MahouPoolSize = 100f;

    @Override
    public boolean GetManhouPool() {return GetManhouPool;}

    @Override
    public void SetMahouPool(boolean Unlocked) {this.GetManhouPool = Unlocked;}

    @Override
    public float MahouPoolValue() {return MahouPool;}

    @Override
    public void MahouPoolSize(int MahouPoolSize) {this.MahouPoolSize = MahouPoolSize;}

    @Override
    public void SetMahouPoolVaule(float mahou) {
        this.MahouPool = Math.max(0, Math.min(mahou, MahouPoolSize));
    }

    @Override
    public void ConsumeFromMahouPool(float mahou) {this.MahouPool = Math.max(0, this.MahouPool - mahou);}

    @Override
    public void RegenerateMahouPool(float mahou) { this.MahouPool = Math.max(0, this.MahouPool + mahou);}

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag NBT = new CompoundTag();
        NBT.putBoolean("HasMahouPool", this.GetManhouPool);
        NBT.putFloat("MahouPool", this.MahouPool);
        NBT.putInt("MahouPoolSize", (int) this.MahouPoolSize);
        return NBT;
    }

    @Override
    public void deserializeNBT(CompoundTag NBT) {
        if (NBT.contains("HasMahouPool")){
            this.GetManhouPool = NBT.getBoolean("HasMahouPool");
        }
        if (NBT.contains("MahouPool")){
            this.MahouPool = NBT.getFloat("MahouPool");
        }
        if (NBT.contains("MahouPoolSize")){
            this.MahouPoolSize = NBT.getInt("MahouPoolSize");
        }
    }

}
