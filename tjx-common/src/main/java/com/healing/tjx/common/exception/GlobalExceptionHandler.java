package com.healing.tjx.common.exception;

import com.healing.tjx.common.api.BasicsResult;
import com.healing.tjx.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @作者: tjx
 * @描述: 全局异常捕获处理
 * @创建时间: 创建于11:19 2020-12-14
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public BasicsResult handleValidException(ApiException e) {
        if (e.getResultCode() != null) {
            return BasicsResult.failed(e.getResultCode());
        }
        return BasicsResult.failed(e.getMessage());
    }

    /**
     * 捕获 参数校验失败
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BasicsResult handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        log.info("message:{}", message);

        return CommonResult.validateFailed(message);
    }
}
