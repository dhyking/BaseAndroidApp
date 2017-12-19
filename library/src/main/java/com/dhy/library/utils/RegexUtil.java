package com.dhy.library.utils;

import java.util.regex.Pattern;

/**
 * Created by dhy on 2017/12/19.
 */

public class RegexUtil {
    public final static String REGEX_MOBILE = "^1(3|5|8)\\d\\d{8}$";   //手机号码验证
    public final static String REGEX_PASSWORD = "^\\w{6,12}$";  //密码验证
    public final static String REGEX_CHINESE = "^[\u4e00-\u9fa5]+$";    //验证汉字
    public final static String REGEX_SMS = "^\\d{6}$";    //短信验证码
    public static final String REGEX_EMAIL = "^(\\w+[-|\\.]?)+\\w@(\\w+(-\\w+)?\\.)+[a-zA-Z]{2,}$"; //email
    public static final String REGEX_ID_CARD = "(^\\d{17}[0-9Xx]$)|(^\\d{15}$)";   //身份证
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?"; //url


    public RegexUtil() {
    }

    public static RegexUtil getInstance() {
        return RegexHolder.newInstance;
    }

    private static class RegexHolder{
        private final static RegexUtil newInstance = new RegexUtil();
    }


    /**
     * 手机号码验证
     * @param mobile
     * @return
     */
    public boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE,mobile);
    }

    /**
     * 密码验证
     * @param passWord
     * @return
     */
    public boolean isPassWord(String passWord){
        return Pattern.matches(REGEX_PASSWORD,passWord);
    }

    /**
     * 判读是否为中文
     * @param chinese
     * @return
     */
    public boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE,chinese);
    }

    /**
     * 判断是否为短信验证码
     * @param smsCode
     * @return
     */
    public boolean isSmsCode(String smsCode){
        return Pattern.matches(REGEX_SMS,smsCode);
    }

    /**判断email
     * @param email
     * @return
     */
    public boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL,email);
    }

    /**
     * 判断URL
     * @param url
     * @return
     */
    public boolean isURl(String url) {
        return Pattern.matches(REGEX_URL,url);
    }

    /**
     * 判断身份证
     * @param idCard
     * @return
     */
    public boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD,idCard);
    }
}
