package cn.edu.qdu.exception;

/**
 * 密码格式错误的异常
 * Created by Adam on 2019/5/4 12:19.
 */
public class PasswordFormatWrongException extends RuntimeException{

    public PasswordFormatWrongException() {
    }

    public PasswordFormatWrongException(String message) {
        super(message);
    }

    public PasswordFormatWrongException(Throwable cause) {
        super(cause);
    }

}
