package com.jym.car.exception;

import lombok.Data;

/**
 * @author lb
 */
@Data
public class MyException extends RuntimeException{
    private String msg;
   // private int code = 500;

    public MyException(String msg){
        super(msg);
        this.msg = msg;
    }

    public MyException() {
    }
}
