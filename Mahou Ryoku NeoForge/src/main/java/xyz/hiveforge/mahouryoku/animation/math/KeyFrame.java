package xyz.hiveforge.mahouryoku.animation.math;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;

public record KeyFrame(float TimeStamp, Vector3f position, Quaternionf rotation) {
    public class BoneAnimation{
        private final String name;
        private final List<KeyFrame> keyFrames;

        public BoneAnimation(String name, List<KeyFrame> keyFrames) {
            this.name = name;
            this.keyFrames = keyFrames;
        }
        public String getName() {return name;}
        public List<KeyFrame> getKeyFrames() {return keyFrames;}
    }
}
