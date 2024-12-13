import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class RSAEncryption {

    public static void main(String[] args) {
        try {
            String encryptedMessage = encrypt("text you want to encrypt", (Common.publicKeyValue));
            System.out.println("Encrypted Message: " + encryptedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Encrypt plain text
    public static String encrypt(String plainText, String publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(Common.RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,KeyUtil.getPublicKeyFromString(publicKey));
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static class KeyUtil {
    // Convert Base64 string to PublicKey
    public static PublicKey getPublicKeyFromString(String publicKeyStr) throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
        return (RSAPublicKey) kf.generatePublic(keySpecX509);
        }
    }
}