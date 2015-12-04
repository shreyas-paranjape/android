package com.android.volley.toolbox;

import android.util.Base64;

import java.nio.charset.Charset;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypt {

    public static String encrypt(String value, String encryptionKey) {
        try {
            byte[] ivBytes = getIVBytes();
            Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, encryptionKey, ivBytes);
            byte[] valueBytes = cipher.doFinal(value.getBytes(Charset.forName("UTF-8")));
            byte[] result = new byte[ivBytes.length + valueBytes.length];
            System.arraycopy(ivBytes, 0, result, 0, ivBytes.length);
            System.arraycopy(valueBytes, 0, result, ivBytes.length, valueBytes.length);
            return Base64.encodeToString(result, Base64.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String data, String decryptionKey) {
        try {
            byte[] value = Base64.decode(data, Base64.DEFAULT);
            Cipher cipher =
                    getCipher(Cipher.DECRYPT_MODE,
                            decryptionKey,
                            Arrays.copyOfRange(value, 0, 16));
            return new String(cipher.doFinal(Arrays.copyOfRange(value, 16, value.length)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static Cipher getCipher(int mode, String seed, byte[] ivBytes) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
            Key keySpec = new SecretKeySpec(getRawKey(seed), "AES");
            cipher.init(mode, keySpec, ivParameterSpec);
            return cipher;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getRawKey(String seed) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed.getBytes(Charset.forName("UTF-8")));
            keyGenerator.init(128, secureRandom);
            return keyGenerator.generateKey().getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getIVBytes() {
        try {
            byte[] ivBytes = new byte[16];
            SecureRandom.getInstance("SHA1PRNG").nextBytes(ivBytes);
            return ivBytes;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
