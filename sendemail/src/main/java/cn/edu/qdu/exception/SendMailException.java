package cn.edu.qdu.exception;

/**
 * Created by Adam on 2019/4/28 14:56.
 */
public class SendMailException extends Exception{

    public SendMailException() {
    }

    public SendMailException(String message) {
        super(message);
    }

    public SendMailException(Throwable cause) {
        super(cause);
    }

}
