package cn.edu.qdu.exception;

/**
 * Created by Adam on 2019/5/5 22:07.
 */
public class PasswordNotCorrectException extends RuntimeException{

    public PasswordNotCorrectException() {
    }

    public PasswordNotCorrectException(String message) {
        super(message);
    }

    public PasswordNotCorrectException(Throwable cause) {
        super(cause);
    }

}
