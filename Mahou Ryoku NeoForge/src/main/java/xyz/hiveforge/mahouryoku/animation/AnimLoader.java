package xyz.hiveforge.mahouryoku.animation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import xyz.hiveforge.mahouryoku.animation.controller.AnimBone;
import xyz.hiveforge.mahouryoku.animation.math.KeyFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnimLoader {
    public static List<AnimBone> loadFromJson(String JsonString, String animName) {
        List<AnimBone> bones = new ArrayList<>();
        JsonObject root = JsonParser.parseString(JsonString).getAsJsonObject();
        JsonObject anims = root.getAsJsonObject("animations");
        JsonObject targetAnim = anims.getAsJsonObject(animName);
        JsonObject bonesJson = targetAnim.getAsJsonObject("bones");

        for (Map.Entry<String, JsonElement> entry : bonesJson.entrySet()) {
            String BoneName = entry.getKey();
            JsonArray FramesJson = entry.getValue().getAsJsonArray();
            List<KeyFrame> Keyframes = new ArrayList<>();

            for (JsonElement FrameElement : FramesJson) {
                JsonObject f = FrameElement.getAsJsonObject();
                float time = f.get("time").getAsFloat();
                JsonArray p = f.getAsJsonArray("pos");
                Vector3f pos = new Vector3f(p.get(0).getAsFloat(), p.get(1).getAsFloat(), p.get(2).getAsFloat());

                JsonArray r = f.getAsJsonArray("rot");
                Quaternionf rot = new Quaternionf(r.get(0).getAsFloat(), r.get(1).getAsFloat(), r.get(2).getAsFloat(), r.get(3).getAsFloat());

                Keyframes.add(new KeyFrame(time, pos, rot));
            }

            bones.add(new AnimBone(BoneName, Keyframes));
        }
        return bones;
    }
}
