package faddy.backend.user.service;

import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.ServerProcessingException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

@Service
public class UserIdEncryptionUtil {

    @Value("${user.id-encryption.public-key}")
    private String publicKeyString;

    @Value("${user.id-encryption.private-key}")
    private String privateKeyString;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct
    public void init() {
        try {
            // 공개키 초기화
            X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString.getBytes()));
            KeyFactory keyFactoryPublic = KeyFactory.getInstance("RSA");
            publicKey = keyFactoryPublic.generatePublic(keySpecPublic);

            // 개인키 초기화
            PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString.getBytes()));
            KeyFactory keyFactoryPrivate = KeyFactory.getInstance("RSA");
            privateKey = keyFactoryPrivate.generatePrivate(keySpecPrivate);
        } catch (Exception e) {
            throw new ServerProcessingException(ExceptionCode.GENERATE_KEY_PAIR_ERROR);
        }
    }

    public String encryptUserId(Long userId) {
        try {
            // userId 암호화
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(userId.toString().getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new ServerProcessingException(ExceptionCode.ENCRYPT_USER_ID_ERROR);
        }
    }

    public Long decryptUserId(String encryptedUserId) {
        try {
            // 암호화된 userId 복호화
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedUserId));
            return Long.parseLong(new String(decryptedBytes));
        } catch (Exception e) {
            throw new ServerProcessingException(ExceptionCode.DECRYPT_USER_ID_ERROR);
        }
    }
}


