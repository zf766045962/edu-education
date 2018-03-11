package com.util.normal;

import com.common.ChpMode;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 安全加密方式
 *
 * @author pgs
 * @version 1.0
 */
public class SecureUtils {

    /**
     * 加密
     *
     * @param cm      加密方式
     * @param message 待加密数据
     * @param sKey    AES秘钥
     * @return 加密后的数据
     * @throws Exception 加密失败
     */
    public static String encrypt(ChpMode cm, String message, String sKey)
            throws Exception {
        String ret = "";
        switch (cm) {
            case AES:
                ret = parseByte2HexStr(AESEncode(message, sKey));
                break;
            case MD5:
                ret = MD5Encode(message);
                break;
            default:
                break;
        }

        return ret;
    }

    /**
     * 解密
     *
     * @param cm      加密方式
     * @param message 待加密数据
     * @param sKey    AES秘钥
     * @return 解密后的数据
     * @throws Exception 解密失败
     */
    public static String decrypt(ChpMode cm, String message, String sKey)
            throws Exception {
        String ret = "";
        switch (cm) {
            case AES:
                ret = new String(AESDecode(parseHexStr2Byte(message), sKey),
                        "UTF-8");
                break;
            default:
                break;
        }

        return ret;
    }

    /**
     * md5 加密
     *
     * @param input 待加密数据
     * @return 加密后的数据
     * @throws UnsupportedEncodingException 格式不支持
     */
    private static String MD5Encode(String input)
            throws UnsupportedEncodingException {
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 输入的字符串转换成字节数组
            byte[] inputByteArray = input.getBytes("UTF-8");
            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 字符数组转换成字符串返回
            return parseByte2HexStr(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * encoded message
     *
     * @param message origin message
     * @param sKey    origin privateKey
     * @return 字节数组
     * @throws Exception 加密失败
     */
    private static byte[] AESEncode(String message, String sKey)
            throws Exception {
        byte[] ret = new byte[1];

        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(sKey.getBytes("UTF-8"));
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = message.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            ret = cipher.doFinal(byteContent);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("加密失败");
        }

        return ret;
    }

    /**
     * decode from encoded message
     *
     * @param message encoded message
     * @param sKey    origin privateKey
     * @throws Exception 解密失败
     */
    private static byte[] AESDecode(byte[] message, String sKey)
            throws Exception {
        byte[] ret = new byte[1];

        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(sKey.getBytes("UTF-8"));
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            ret = cipher.doFinal(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("解密失败");
        }

        return ret;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf 二进制数组
     * @return 16进制字符串
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (byte aBuf : buf) {
            String hex = Integer.toHexString(aBuf & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr 16进制字符串
     * @return 二进制字符串
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}