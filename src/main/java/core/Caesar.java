package core;

import core.codecs.StableCodec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Caesar {

    private static final StableCodec CODEC = StableCodec.PWC255;
    private static final String HASHING_ALGORITHM = "SHA-256";
    private static final String KEYGEN_ALGORITHM = "AES";
    private static final Charset KEYGEN_CHARSET = StandardCharsets.UTF_16;
    private static final String CIPHER_ALGORITHM = "AES/CBC/NoPadding";
    private static final byte[] IV = new byte[] {-119, -45, 23, -63, 127, -74, 63, 51, 18, -116, -10, -87, -22, -115, 58, 96};
    private static final IvParameterSpec IV_SPEC = new IvParameterSpec(IV);

    private int size;
    private Cipher cipher;
    private MessageDigest hashing;

    public Caesar(int size) {
        try {
            this.size = size;
            this.cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            this.hashing = MessageDigest.getInstance(HASHING_ALGORITHM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SecretKeySpec key(String string) {
        return new SecretKeySpec(this.hashing.digest(string.getBytes(KEYGEN_CHARSET)), KEYGEN_ALGORITHM);
    }

    public byte[] encrypt(String key, String string) {
        try {
            byte[] decrypted = CODEC.toBytes(string, this.size);
            this.cipher.init(Cipher.ENCRYPT_MODE, key(key), IV_SPEC);
            return this.cipher.doFinal(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(String key, byte[] bytes) {
        try {
            this.cipher.init(Cipher.DECRYPT_MODE, key(key), IV_SPEC);
            byte[] decrypted = this.cipher.doFinal(bytes);
            return CODEC.toString(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
