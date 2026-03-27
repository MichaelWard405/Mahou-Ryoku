package io.github.mahouryoku.mahou.storage.mahoupool;

public interface MahouPoolInterFace {
    boolean GetManhouPool();
    void SetMahouPool(boolean Unlocked);
    float MahouPoolValue();
    void MaxMahouPoolSize(float MaxMahouPoolSize);
    float MahouPoolSize();
    void SetMahouPoolVaule(float mahou);
    void ConsumeFromMahouPool(float mahou);
    void RegenerateMahouPool(float mahou);
}
