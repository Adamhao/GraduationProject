package cn.edu.qdu.exception;

/**
 * Created by Adam on 2019/5/5 21:52.
 */
public class NotAuthorizedException extends RuntimeException{

    public NotAuthorizedException() {
    }

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException(Throwable cause) {
        super(cause);
    }

}
