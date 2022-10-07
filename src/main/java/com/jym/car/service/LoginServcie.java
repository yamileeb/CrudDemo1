package com.jym.car.service;

import com.jym.car.model.entity.User;
import com.jym.car.model.result.Result;

/**
 * @author lb
 */
public interface LoginServcie {
    Result login(User user);

    Result logout();
}
