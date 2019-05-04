package cn.edu.qdu.util;

/**
 * 验证码工具类
 * Created by Adam on 2019/5/4 13:26.
 */
public class VerificationCodeUtil {

    /**
     * 生成六位数纯数字验证码
     * @return 生成的验证码
     */
    public static String generateCode() {
        return ((int)(Math.random() * 900000) + 100000) + "";
    }

}
