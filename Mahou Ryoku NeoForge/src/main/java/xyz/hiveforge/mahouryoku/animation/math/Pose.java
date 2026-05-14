package xyz.hiveforge.mahouryoku.animation.math;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public record Pose(Vector3f pos, Quaternionf rot) {
    public static Pose identity() {
        return new Pose(new Vector3f(0, 0, 0), new Quaternionf(0, 0, 0, 1));
    }
}