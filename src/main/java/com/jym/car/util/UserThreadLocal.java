package com.jym.car.util;

import com.jym.car.model.entity.LoginUser;
import com.jym.car.model.entity.User;

/**
 * 保存用户信息
 * 线程变量隔离，每个线程都会绑定一个ThreadLocal，这样就不会起冲突
 */
public class UserThreadLocal {

    private UserThreadLocal() {
    }

    private static final ThreadLocal<LoginUser> LOCAL = new ThreadLocal<>();

    public static void put(LoginUser user) {
        LOCAL.set(user);
    }

    public static LoginUser get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}

