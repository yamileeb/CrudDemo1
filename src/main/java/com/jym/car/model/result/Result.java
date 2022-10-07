package com.jym.car.model.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lb
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;


    public static <T> Result ok(T t) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(t);
        return result;
    }

    public static <T> Result error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(201);
        result.setMsg(msg);
        return result;
    }
}
