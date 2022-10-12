package com.jym.car.controller;

import com.jym.car.model.entity.User;
import com.jym.car.model.result.Result;
import com.jym.car.service.LoginServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginServcie loginServcie;

    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        return loginServcie.login(user);
    }

    @GetMapping("/user/login")
    public Result login(){
        return Result.ok("hello world!!!!!!!!!!!!!!!!!!!!!!");
    }

    @PostMapping("/user/logout")
    public Result logout(){
        return loginServcie.logout();
    }
}
