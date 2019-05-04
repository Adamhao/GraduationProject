package cn.edu.qdu.util;

import cn.edu.qdu.exception.PasswordFormatWrongException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 注册登录修改密码等
 * Created by Adam on 2019/5/4 11:56.
 */
public class AccountUtil {

    /**
     * 判断密码格式是否正确
     * @param password 密码
     */
    public static void isRightPasswordFormat(String password) {
        //判断密码是否为空
        if(password == null || "".equals(password)) {
            throw new PasswordFormatWrongException("密码为空");
        }
        //判断密码位数是否符合
        if(password.length() < 8 || password.length() > 16) {
            throw new PasswordFormatWrongException("密码长度应在[8-16]位");
        }
        //判断密码是否包含字母和数字
        Pattern pattern = Pattern.compile("[0-9]*");
        if(pattern.matcher(password).matches()){
            throw new PasswordFormatWrongException("密码不能全为数字");
        }
        pattern = Pattern.compile("[a-zA-Z]*");
        if(pattern.matcher(password).matches()){
            throw new PasswordFormatWrongException("密码不能全为字母");
        }
    }

    /**
     * 判断输入的密码是否和数据库中一致
     * @param right 数据库中的密码
     * @param input 输入的密码
     * @return boolean
     */
    public static boolean isPasswordRight(String right,String input) {
        //判断密码是否为空
        if(input == null || "".equals(input)) {
            return false;
        }
        return right.equals(MD5Util.MD5Encode(input));
    }

}
