package xyz.hiveforge.mahouryoku.magic_systems.mahou;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;


public class MahouPool implements MahouPoolInterface{
    public boolean GetMahouPool = false;
    public float MahouPool = 1f;
    public float MahouPoolSize = 100f;
    public float Affinity = 0;
    public float Eccentricity = 0;

    public MahouPool(boolean GetMahouPool, float MahouPool, float MahouPoolSize, float Affinity, float Eccentricity){
        this.GetMahouPool = GetMahouPool;
        this.MahouPool = MahouPool;
        this.MahouPoolSize = MahouPoolSize;
        this.Affinity = Affinity;
        this.Eccentricity = Eccentricity;
    }
    public MahouPool(){}

    public static final MapCodec<MahouPool> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.BOOL.fieldOf("GetMahouPool").forGetter(xyz.hiveforge.mahouryoku.magic_systems.mahou.MahouPool::GetManhouPool),
            Codec.FLOAT.fieldOf("MahouPool").forGetter(xyz.hiveforge.mahouryoku.magic_systems.mahou.MahouPool::MahouPoolValue),
            Codec.FLOAT.fieldOf("MahouPoolSize").forGetter(xyz.hiveforge.mahouryoku.magic_systems.mahou.MahouPool::MahouPoolSize),
            Codec.FLOAT.fieldOf("Affinity").forGetter(xyz.hiveforge.mahouryoku.magic_systems.mahou.MahouPool::Affinity),
            Codec.FLOAT.fieldOf("Eccentricity").forGetter(xyz.hiveforge.mahouryoku.magic_systems.mahou.MahouPool::Eccentricity)
    ).apply(instance, MahouPool::new));



    @Override
    public boolean GetManhouPool() {return GetMahouPool;}
    @Override
    public void SetMahouPool(boolean Unlocked) {this.GetMahouPool = Unlocked;}
    @Override
    public float MahouPoolValue() {return MahouPool;}
    @Override
    public void MaxMahouPoolSize(float MaxMahouPoolSize) {this.MahouPoolSize = MaxMahouPoolSize;}
    @Override
    public float MahouPoolSize() {return MahouPoolSize;}
    @Override
    public void SetMahouPoolVaule(float mahou) {this.MahouPool = Math.max(1, Math.min(mahou, MahouPoolSize));}
    @Override
    public void ConsumeFromMahouPool(float mahou) {this.MahouPool = Math.max(1, this.MahouPool - mahou);}
    @Override
    public void RegenerateMahouPool(float mahou) {this.MahouPool = Math.min(this.MahouPool + mahou, this.MahouPoolSize);}
    @Override
    public float Affinity() {return Affinity;}
    @Override
    public float Eccentricity() {return Eccentricity;}
    @Override
    public void SetAffinity(float Affinity) {this.Affinity = Affinity;}
    @Override
    public void SetEccentricity(float Eccentricity) {this.Eccentricity = Eccentricity;}
}
