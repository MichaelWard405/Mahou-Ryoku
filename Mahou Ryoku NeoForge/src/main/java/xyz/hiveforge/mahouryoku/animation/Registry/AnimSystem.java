package xyz.hiveforge.mahouryoku.animation.Registry;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AnimSystem {
    private static final Map<UUID, ActiveAnimation> PLAYING_ANIMATIONS = new ConcurrentHashMap();

    public static void PlayBlenderAnim(UUID PlayerUuid, String AnimName, boolean loop) {
        PLAYING_ANIMATIONS.put(PlayerUuid, new ActiveAnimation(AnimName, System.currentTimeMillis(), loop));}

    public static ActiveAnimation getActiveAnimation(UUID playerUuid) {
        return PLAYING_ANIMATIONS.get(playerUuid);}

    public static void StopAnim(UUID PlayerUuid) {
        PLAYING_ANIMATIONS.remove(PlayerUuid);}

    public record ActiveAnimation(String Name, long StartTime, boolean loop) {}
}
