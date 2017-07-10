package tang.tangry.common.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import tang.tangry.common.utils.CodeUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tryu on 2017/7/8.
 * 自定义密码验证，MyRealm里生成info后转到这里。
 */

public class MyCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        try {
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
            String password = String.valueOf(usernamePasswordToken.getPassword());
            Object tokenCredentials= CodeUtils.encrypt(password);
            Object databaseCredentials = getCredentials(info);
            return equals(tokenCredentials,databaseCredentials);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
