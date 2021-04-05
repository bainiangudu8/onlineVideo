package com.java.onlinevideo.exception;

import com.java.onlinevideo.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-14 20:35
 */
@ControllerAdvice
public class CustomExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handler(Exception e){

        logger.error("[系统异常]{}",e);

        if(e instanceof CHException){
            CHException chException = (CHException)e;
            return JsonData.buildError(chException.getCode(),chException.getMsg());
        }else{
            return JsonData.buildError("全局异常，未知错误");
        }
    }
}
