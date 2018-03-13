package com.util.normal;

/**
 * 正则验证工具类
 *
 * @author pgs
 * @version 1.0
 */
public class RegexUtil {
    /**
     * 验证是否为合法的手机号码(中国)
     *
     * @param phoneNumber 手机号码
     * @return a boolean
     */
    public static boolean isValidPhoneNumberCN(String phoneNumber) {
        return StringUtils.isEmpty(phoneNumber) && phoneNumber.matches("^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$");
    }

    /**
     * 验证是否为合法的Email
     *
     * @param email 邮箱
     * @return a boolean
     */
    public static boolean isValidEmail(String email) {
        return StringUtils.isEmpty(email) && email.matches("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
    }

    /**
     * 验证是否为合法的用户名
     *
     * @param username 用户名
     * @return a boolean
     */
    public static boolean isValidUsername(String username) {
        return StringUtils.isEmpty(username) && username.matches("^([a-zA-Z]|[\\u4e00-\\u9fa5])+\\w*[\\u4e00-\\u9fa5]*$");
    }

    /**
     * 验证是否为合法的URL
     *
     * @param url 网址
     * @return boolean
     */
    public static boolean isValidURL(String url) {
        return StringUtils.isEmpty(url) && url.matches("[a-zA-z]+://[^\\s]*");
    }
}