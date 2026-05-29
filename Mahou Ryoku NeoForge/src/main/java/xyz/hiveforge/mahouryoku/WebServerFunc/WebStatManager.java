package xyz.hiveforge.mahouryoku.WebServerFunc;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import xyz.hiveforge.mahouryoku.Registries.PlayerDataAttachment;
import xyz.hiveforge.mahouryoku.Registries.RaceRegisteries;
import xyz.hiveforge.mahouryoku.race_system.PlayerRaceData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class WebStatManager {
    //===============
    //  HTTP Client
    //===============
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    private static final String BASE_URL = "http://localhost:8080/api";

    //===================================
    //  Pull Players Data From WebServer
    //===================================
    public static void FetchPlayerStatsAsync(ServerPlayer Player) {
        String URL = BASE_URL + "/get-stats?uuid=" + Player.getUUID().toString();
        HttpRequest Request = HttpRequest.newBuilder().uri(URI.create(URL)).GET().build();
        HTTP_CLIENT.sendAsync(Request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(Response -> HandleServerResponse(Player, Response));
    }

    //===========================
    //   Send An Unlock Request
    //===========================
    public static void SendUnlockRequestAsync(ServerPlayer Player, String RaceID) {
        String URL = BASE_URL + "/unlock-race";
        String jsonBody = String.format("{\"uuid\":\"%s\",\"race\":\"%s\"}", Player.getUUID().toString(), RaceID);

        HttpRequest Request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HTTP_CLIENT.sendAsync(Request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(Response -> HandleServerResponse(Player, Response));
    }

    //===========================
    //  Server Response Handler
    //===========================
    private static void HandleServerResponse(ServerPlayer Player, HttpResponse<String> Response) {
        try {
            if (Response.statusCode() == 200) {
                JsonObject json = JsonParser.parseString(Response.body()).getAsJsonObject();
                String PayLoad = json.get("payload").getAsString();
                String Signature = json.get("signature").getAsString();

                if (TokenVerifier.VerifyToken(PayLoad, Signature)) {
                    String[] Parts = PayLoad.split(":");
                    String ReturnedUUID = Parts[0];
                    List<String> UnlockedList = List.of(Parts[1].split(","));

                    if (ReturnedUUID.equals(Player.getUUID().toString())) {
                        ServerLifecycleHooks.getCurrentServer().execute(() -> {
                            PlayerRaceData Data = Player.getData(PlayerDataAttachment.PLAYER_RACE);
                            Data.SetUnlockedRaces(UnlockedList);
                            if (!UnlockedList.contains(Data.GetCurrentRaceID())) {
                                Data.SetRace(RaceRegisteries.HUMAN, Player);
                            }
                            Player.sendSystemMessage(Component.literal("Race Options Synced"));
                        });
                    }
                }
            }
        } catch (Exception e){e.printStackTrace();}
    }
}
