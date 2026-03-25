package io.github.mahouryoku.mana;

public interface ManaPoolInterface {
    float getManaPool();
    void setManaPool(float mana);
    void consumeManaPool(float mana);
    void regenerateManaPool();
}