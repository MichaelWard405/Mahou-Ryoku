package io.github.mahouryoku.mana;

public interface ManaInterface {
    float getMana();
    void setMana(float mana);
    void consume(float mana);
    void regenerate();
}