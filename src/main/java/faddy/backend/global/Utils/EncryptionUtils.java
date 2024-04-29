package faddy.backend.global.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class EncryptionUtils {

    private static String SECRET_KEY;

    private EncryptionUtils(@Value("${util.secret-key}") String secretKey) {
        SECRET_KEY = secretKey;
    }

    public static String encryptEntityId(Long entityId) throws Exception {

        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(entityId.toString().getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static Long decryptEntityId(String encryptedEntityId) throws Exception {

        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedEntityId));

        return Long.parseLong(new String(decryptedBytes));
    }


}