package com.util.exception;

import com.common.result.CodeMsg;
import com.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;


/**
 * 异常统一拦截
 *
 * @author 潘根山
 * @create 2018-02-05 17:03
 * @since 1.0.0
 */
@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception ex) {
        //处理绑定异常
        if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            List<ObjectError> errors = bindException.getAllErrors();
            ObjectError error = errors.get(0);
            String errMsg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(errMsg));
        }
        if (ex instanceof LoginException) {
            return Result.error(CodeMsg.LOGIN_VALID);
        }
        if (ex instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException missingServletRequestParameterException = (MissingServletRequestParameterException) ex;
            String param = missingServletRequestParameterException.getParameterName();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(param + "不能为空"));
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException methodArgumentTypeMismatchException = (MethodArgumentTypeMismatchException) ex;
            String param = methodArgumentTypeMismatchException.getName();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(param + "参数类型错误"));
        }
        ex.printStackTrace();
        logger.info("错误信息:{},错误原因:{}", ex.getMessage(), ex.getCause());
        return Result.error(CodeMsg.SERVER_ERROR);
    }
}