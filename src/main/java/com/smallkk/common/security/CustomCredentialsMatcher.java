package com.smallkk.common.security;

import com.smallkk.common.utils.MD5Utils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
/**
 * Created By  醉美柳舞之众星捧月
 * @author song
 * @date 2017/10/31 13:52
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
    /**
     * 自定义密码验证
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        try {
            UsernamePasswordToken usertoken = (UsernamePasswordToken) token;
            String password = String.valueOf(usertoken.getPassword());
            Object tokenCredentials = MD5Utils.encrptyPassword(password);
            Object accountCredentials = getCredentials(info);
            return equals(tokenCredentials, accountCredentials);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
