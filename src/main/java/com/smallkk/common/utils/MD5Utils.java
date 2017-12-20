package com.smallkk.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/19 22:09
 */
public class MD5Utils {
    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/19 22:09
     * Description  消息摘要算法 人类看不懂的
     */
    public static String encrptyPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
//JDK9.0已经不再支持BASE64Encoder;rj.jar包已经从JDK里面移除;
        //用import java.util.Base64;来替换
//        BASE64Encoder base64Encoder = new BASE64Encoder();
//        String result = base64Encoder.encode(md5.digest(password.getBytes("utf-8")));
        Base64.Encoder encoder = Base64.getEncoder();
        String result = encoder.encodeToString(md5.digest(password.getBytes("utf-8")));
        // 通过utf-8的方式生成字节码 把密码转换 ;再让BASE64Encoder转换一下,防止加密乱码啊什么的
        return result;
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/19 22:17
     * Description   判断传入的密码和数据库中的密码是否一致
     */
    public static boolean checkPassword(String inputPwd, String dbPwd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //分别传入的是  用户输入传入的密码,数据库中已有的传入的密码
        String result = encrptyPassword(inputPwd);
        if (result.equals(dbPwd)) {
            return true;
        } else {
            return false;
        }
    }
}
