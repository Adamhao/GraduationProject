package cn.edu.qdu.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Created by Adam on 2019/5/5 0:37.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    // 处理所有其他不能自定义的异常
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiErrorResponse> handleAnyException(Exception ex, WebRequest request) {
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .withError_code(HttpStatus.INTERNAL_SERVER_ERROR.name()) //500
                .withMessage(ex.getLocalizedMessage()).build();

        return new ResponseEntity<>(response, response.getStatus());
    }

    // 数据完整性违规异常
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConflictException(DataIntegrityViolationException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withError_code(status.CONFLICT.name()) //409
                .withMessage(ex.getLocalizedMessage()).build();

        return new ResponseEntity<>(response, response.getStatus());
    }

    // 方法参数类型不匹配异常
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withError_code(status.BAD_REQUEST.name()) //400
                .withMessage(ex.getLocalizedMessage()).build();

        return new ResponseEntity<>(response, response.getStatus());
    }

    // Http消息不可读异常
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "格式错误的JSON请求 ";
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withError_code("格式错误")
                .withMessage(ex.getLocalizedMessage())
                .withDetail(error+ex.getMessage()) .build();

        return new ResponseEntity<>(response, response.getStatus());
    }

    // 密码格式不正确异常
    @ExceptionHandler(value = PasswordFormatWrongException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(PasswordFormatWrongException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withError_code("500")
                .withMessage(ex.getLocalizedMessage())
                .withDetail(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, response.getStatus());
    }

}
