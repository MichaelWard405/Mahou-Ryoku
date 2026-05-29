package xyz.hiveforge.mahouryoku.WebServerFunc;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class TokenVerifier {
    private static final String PUBLIC_KEY_BASE64 = "REPLACE_WITH_KEY"; // PUBLIC KEY!


    //======================================
    //      Cryptographic Verification
    //======================================
    public static boolean VerifyToken(String PayLoad, String SignatureBase64) {
        try {
            byte[] KeyBytes = Base64.getDecoder().decode(PUBLIC_KEY_BASE64.replaceAll("\\s+", ""));
            X509EncodedKeySpec SPEC = new X509EncodedKeySpec(KeyBytes);
            KeyFactory KF = KeyFactory.getInstance("RSA");
            PublicKey PublicKey = KF.generatePublic(SPEC);

            Signature SIG = Signature.getInstance("SHA256withRSA");
            SIG.initVerify(PublicKey);
            SIG.update(PayLoad.getBytes(StandardCharsets.UTF_8));

            return SIG.verify(Base64.getDecoder().decode(SignatureBase64.replaceAll("\\s+", "")));
        } catch (Exception e){return false;}
    }
}
