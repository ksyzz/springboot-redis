package com.example.shiro;


import com.example.shiro.model.json.ErrorInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.PersistenceException;

/**
 * @author fengqian
 * @since <pre>2018/06/27</pre>
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 处理请求异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler
    protected ResponseEntity<Object> handleRequestException(AuthorizationException ex, WebRequest request) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), "没有权限", ex.getMessage());
        return handleExceptionInternal(ex, errorInfo, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
   /**
     * 处理请求异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler
    protected ResponseEntity<Object> handleRequestException(AuthenticationException ex, WebRequest request) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), "认证失败", ex.getMessage());
        return handleExceptionInternal(ex, errorInfo, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
