package sec.common;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Utilities {

    public final String CLIENT_PUBLIC_KEY_FILE = "clientPublicKey.key";
    public final String SERVER_PUBLIC_KEY_FILE = "serverPublicKey.key";

    public PublicKey getPublicKey(String keyFile) throws Exception {
        byte[] publicKeyBytes = Files.readAllBytes(Paths.get(keyFile));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }

    public String encryptMessage(String secretMessage, KeyPair keys) throws Exception {
        SecretKey aesKey = getAesKey(keys.getPrivate());
        byte[] messageBytes = secretMessage.getBytes();

        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] aesIVBytes = Files.readAllBytes(Path.of("aes_IV.bin"));
        IvParameterSpec ivSpec = new IvParameterSpec(aesIVBytes);
        aesCipher.init(Cipher.ENCRYPT_MODE,aesKey,ivSpec);
        byte [] encryptedMessage = aesCipher.doFinal(messageBytes);
//        encryptAesKey(aesKey, keys.getPublic());
        return Base64.getEncoder().encodeToString(encryptedMessage);
    }

    public String decryptMessage(String encryptedMessage, KeyPair keys) throws Exception{
        SecretKey aesKey = getAesKey(keys.getPrivate());
        byte[] encryptedMessageBytes = Base64.getDecoder().decode(encryptedMessage);
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] aesIVBytes = Files.readAllBytes(Path.of("aes_IV.bin"));
        IvParameterSpec aesCipherIV = new IvParameterSpec(aesIVBytes);
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey, aesCipherIV);
        byte[] decryptedMessage = aesCipher.doFinal(encryptedMessageBytes);

//        encryptAesKey(aesKey, keys.getPublic());

        return new String(decryptedMessage);
    }

    public SecretKey getAesKey(PrivateKey privateKey) throws Exception{
        String aesFile = "encrypted_aes_key.bin";
        byte[] encryptedAesKeyFromFile = Files.readAllBytes(Paths.get(aesFile));
        Cipher rsaCipher = Cipher.getInstance("RSA");
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedAesKeyBytes = rsaCipher.doFinal(encryptedAesKeyFromFile);
        return new SecretKeySpec(decryptedAesKeyBytes, "AES");
    }

    public void encryptAesKey(SecretKey aesKey, PublicKey publicKey) throws Exception{
        byte[] keyBytes = aesKey.getEncoded();

        Cipher rsaCipher = Cipher.getInstance("RSA");
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedAesKey = rsaCipher.doFinal(keyBytes);
        String filePath = "encrypted_aes_key.bin";
        Files.write(Paths.get(filePath), encryptedAesKey, StandardOpenOption.CREATE);
    }

}
