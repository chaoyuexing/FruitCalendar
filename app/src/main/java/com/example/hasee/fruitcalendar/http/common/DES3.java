package com.example.hasee.fruitcalendar.http.common;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3des
 */
public class DES3 {

    // 向量
    private final static String iv = "01234567";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    /**
     * 生成秘钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public final static String getSecretKey(String IEMI) {
        String secretKey = IEMI.substring(0, 8) + "sdjnzyxxkmdm2016";
        return secretKey;
    }

    /**
     * 3DES加密
     *
     * @param plainText 普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String plainText, String secretKey) {
        try {

            Key deskey = null;
            System.out.println("secretkey.length=" + secretKey.length());
            System.out.println("iv.length=" + iv.length());
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);

            byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
            return Base64Util.encode(encryptData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText, String secretKey) {

        try {

            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            Key deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
            byte[] decryptData = cipher.doFinal(Base64Util.decode(encryptText));
            return new String(decryptData, encoding);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
