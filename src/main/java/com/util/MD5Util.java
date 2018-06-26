package com.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    private static final String RAND = "0123456789abcdefghijklmnopqrstuvwxyz";
    /**
     * 默认密码
     */
    private static final String DEFAULT_PASSWORD = "123456";

    public static String inputPassToFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        System.out.println(str);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        return formPassToDBPass(formPass, saltDB);
    }

    /**
     * 生成随机盐
     */
    public static String randomSalt() {
        Random rd = new Random();
        StringBuilder salt = new StringBuilder();
        int len = RAND.length();
        for (int i = 0; i < 8; i++) {
            int index = rd.nextInt(len);
            salt.append(RAND.charAt(index));
        }
        System.out.println(salt);
        return salt.toString();
    }

    public static String createDefaultPassword(String salt, String userName) {
        return formPassToDBPass(inputPassToFormPass(DEFAULT_PASSWORD), salt);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));//d3b1294a61a07da9b49b6e22b2cbd7f9
//        System.out.println(formPassToDBPass(inputPassToFormPass(DEFAULT_PASSWORD), randomSalt()));
//		System.out.println(inputPassToDbPass("123456", "1a2b3c4d"));//b7797cce01b4b131b433b6acf4add449
    }
}
