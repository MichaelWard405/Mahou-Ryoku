package xyz.hiveforge.mahouryoku.magic_systems.mahou;

public interface MahouPoolInterface {
    boolean GetManhouPool();
    void SetMahouPool(boolean Unlocked);
    float MahouPoolValue();
    void MaxMahouPoolSize(float MaxMahouPoolSize);
    float MahouPoolSize();
    void SetMahouPoolVaule(float mahou);
    void ConsumeFromMahouPool(float mahou);
    void RegenerateMahouPool(float mahou);
    float Affinity();
    float Eccentricity();
    void SetAffinity(float Affinity);
    void SetEccentricity(float Eccentricity);
}
