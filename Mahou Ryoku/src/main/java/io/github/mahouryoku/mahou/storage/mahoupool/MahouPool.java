package io.github.mahouryoku.mahou.storage.mahoupool;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class MahouPool implements MahouPoolInterFace, INBTSerializable<CompoundTag> {
    public boolean GetManhouPool = false;
    public float MahouPool = 1f;
    public float MahouPoolSize = 100f;

    @Override
    public boolean GetManhouPool() {return GetManhouPool;}

    @Override
    public void SetMahouPool(boolean Unlocked) {this.GetManhouPool = Unlocked;}

    @Override
    public float MahouPoolValue() {return MahouPool;}

    @Override
    public void MaxMahouPoolSize(float MaxMahouPoolSize) {this.MahouPoolSize = MaxMahouPoolSize;}

    @Override
    public float MahouPoolSize() {return MahouPoolSize;}

    @Override
    public void SetMahouPoolVaule(float mahou) {
        this.MahouPool = Math.max(1, Math.min(mahou, MahouPoolSize));
    }

    @Override
    public void ConsumeFromMahouPool(float mahou) {this.MahouPool = Math.max(1, this.MahouPool - mahou);}

    @Override
    public void RegenerateMahouPool(float mahou) { this.MahouPool = Math.min(this.MahouPool + mahou, this.MahouPoolSize);}

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag NBT = new CompoundTag();
        NBT.putBoolean("HasMahouPool", this.GetManhouPool);
        NBT.putFloat("MahouPool", this.MahouPool);
        NBT.putFloat("MahouPoolMax", this.MahouPoolSize);
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
        if (NBT.contains("MahouPoolMax")){
            this.MahouPoolSize = NBT.getFloat("MahouPoolMax");
        }
    }

}
