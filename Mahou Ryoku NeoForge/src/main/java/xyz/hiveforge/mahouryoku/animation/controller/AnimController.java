package xyz.hiveforge.mahouryoku.animation.controller;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import xyz.hiveforge.mahouryoku.animation.math.AnimMath;
import xyz.hiveforge.mahouryoku.animation.math.KeyFrame;
import xyz.hiveforge.mahouryoku.animation.math.Pose;

import java.util.List;

public class AnimController {
    private List<AnimBone> ActiveBones;
    private float CurrentTime = 0;
    private boolean IsPlaying = false;
    private boolean Loop = true;

    public void SetAnim(List<AnimBone> bones, boolean Loop) {
        this.ActiveBones = bones;
        this.Loop = Loop;
        this.CurrentTime = 0;
        this.IsPlaying = true;
    }

    public void tick(float PartialTick) {
        if (!IsPlaying || ActiveBones == null) return;

        CurrentTime += (1.0f / 20.0f);

        float MaxTime = GetMaxAnimationTime();
        if (CurrentTime > MaxTime) {
            if (Loop) CurrentTime = 0;
            else IsPlaying = false;
        }
    }

    public Pose CalculateInterpolatedPose(List<KeyFrame> Frames) {
        if (Frames.isEmpty()) return Pose.identity();
        if (Frames.size() == 1) return new Pose(Frames.get(0).position(), Frames.get(0).rotation());

        KeyFrame Start = Frames.get(0);
        KeyFrame End = Frames.get(Frames.size() - 1);

        for (int i = 0; i < Frames.size() - 1; i++) {
            if (CurrentTime >= Frames.get(i).TimeStamp()  && CurrentTime <= Frames.get(i + 1).TimeStamp()) {
                Start = Frames.get(i);
                End = Frames.get(i + 1);
                break;
            }
        }

        float TotalRange = End.TimeStamp() - Start.TimeStamp();
        float Alpha = (CurrentTime - Start.TimeStamp()) / TotalRange;

        Vector3f pos = AnimMath.lerp(Start.position(), End.position(), Alpha);
        Quaternionf rot = AnimMath.slerp(Start.rotation(), End.rotation(), Alpha);

        return new Pose(pos, rot);
    }

    public Pose GetPoseForBone(String BoneName) {
        if (ActiveBones == null) return Pose.identity();

        for (AnimBone bone : ActiveBones) {
            if (bone.getName().equalsIgnoreCase(BoneName)) {
                return CalculateInterpolatedPose(bone.getKeyframes());
            }
        }
        return Pose.identity();
    }

    private float GetMaxAnimationTime() {
        float max = 0f;

        for (AnimBone bone : ActiveBones) {
            if (!bone.getKeyframes().isEmpty()) {
                float LastFrameTime = bone.getKeyframes().get(bone.getKeyframes().size() -1).TimeStamp();
                if (LastFrameTime > max) max = LastFrameTime;
            }
        }
        return max;
    }
}
