package xyz.hiveforge.mahouryoku.animation.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderArmEvent;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import xyz.hiveforge.mahouryoku.animation.controller.AnimController;
import xyz.hiveforge.mahouryoku.animation.math.Pose;
import xyz.hiveforge.mahouryoku.item.Mahou_Items;

public class HandRenderer {

    private static final AnimController CONTROLLER = new AnimController();

    @SubscribeEvent
    public static void onRenderArm(RenderArmEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;
        if (player.getMainHandItem().is(Mahou_Items.MAHOU)) {

            PoseStack poseStack = event.getPoseStack();
            String boneName = event.getArm().name().toLowerCase() + "_arm";
            Pose animPose = CONTROLLER.GetPoseForBone(boneName);
            poseStack.pushPose();
            poseStack.translate(animPose.pos().x, animPose.pos().y, animPose.pos().z);
            poseStack.mulPose(animPose.rot());
        }
    }
}