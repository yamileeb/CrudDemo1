package com.jym.car.common;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.jym.car.exception.MyException;

import org.springframework.util.StringUtils;

/**
 * @author lb
 */
public class Verif {

    public static void varIsNull(Object str){
        if(ObjectUtils.isEmpty(str)){
            throw new MyException("参数错误");
        }
        if(str instanceof String){
            if( !StringUtils.hasText((String) str)){
                throw new MyException("参数错误");
            }
        }
    }

    public static void varIsZero(Integer var,String msg){
        if(ObjectUtils.isNotEmpty(var) && var == 0){
            throw new MyException(msg);
        }
    }
}
