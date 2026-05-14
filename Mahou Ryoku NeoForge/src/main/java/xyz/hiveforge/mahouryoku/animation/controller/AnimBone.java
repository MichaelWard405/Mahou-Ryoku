package xyz.hiveforge.mahouryoku.animation.controller;

import xyz.hiveforge.mahouryoku.animation.math.KeyFrame;
import java.util.List;

public class AnimBone {
    private final String name;
    private final List<KeyFrame> keyframes;

    public AnimBone(String name, List<KeyFrame> keyframes) {
        this.name = name;
        this.keyframes = keyframes;
    }
    public String getName() {
        return name;
    }
    public List<KeyFrame> getKeyframes() {
        return keyframes;
    }
}