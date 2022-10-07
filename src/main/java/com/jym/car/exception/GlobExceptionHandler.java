package com.jym.car.exception;

import com.jym.car.model.result.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lb
 */
@RestControllerAdvice
public class GlobExceptionHandler {

    private static final Logger log = LogManager.getLogger(GlobExceptionHandler.class);

    @ExceptionHandler(MyException.class)
    public ResponseEntity doMyException(MyException e){
        log.error("异常信息",e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.error(e.getMsg()));
    }


    @ExceptionHandler(value =Exception.class)
    public ResponseEntity exceptionHandler(Exception e) throws Exception {
        //抛出AccessDeniedException异常
        if(e instanceof AccessDeniedException){
            log.error("权限异常!原因是:",e);
            throw e;
        }

        log.error("未知异常！原因是:",e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.error("未知异常"));
    }


}
