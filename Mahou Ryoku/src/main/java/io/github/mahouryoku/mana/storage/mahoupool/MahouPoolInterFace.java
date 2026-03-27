package io.github.mahouryoku.mana.storage.mahoupool;

public interface MahouPoolInterFace {
    boolean GetManhouPool();
    void SetMahouPool(boolean Unlocked);
    float MahouPoolValue();
    void MahouPoolSize(int MahouPoolSize);
    void SetMahouPoolVaule(float mahou);
    void ConsumeFromMahouPool(float mahou);
    void RegenerateMahouPool(float mahou);
}
