package tang.tangry.common.utils;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tryu on 2017/6/30.
 */
public class CodeUtils {
    //加密密码
    public static String encrypt(String code) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String cipherText;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //防止返回乱码
        cipherText = base64Encoder.encode(md5.digest(code.getBytes("UTF-8")));
        return cipherText;
    }
}
