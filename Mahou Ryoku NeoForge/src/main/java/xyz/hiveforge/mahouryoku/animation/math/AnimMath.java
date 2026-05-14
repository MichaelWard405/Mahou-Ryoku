package xyz.hiveforge.mahouryoku.animation.math;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class AnimMath {
    public static Vector3f lerp(Vector3f start, Vector3f end, float delta) {
        return new Vector3f(start).lerp(end, delta);
    }
    public static Quaternionf slerp(Quaternionf start, Quaternionf end, float delta) {
        return new Quaternionf(start).slerp(end, delta);
    }
}
