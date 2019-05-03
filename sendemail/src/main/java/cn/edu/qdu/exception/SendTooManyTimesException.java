package cn.edu.qdu.exception;

/**
 * Created by Adam on 2019/5/3 13:53.
 */
public class SendTooManyTimesException extends RuntimeException{

    public SendTooManyTimesException() {
    }

    public SendTooManyTimesException(String message) {
        super(message);
    }

    public SendTooManyTimesException(Throwable cause) {
        super(cause);
    }

}
