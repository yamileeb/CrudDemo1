package com.jym.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jym.car.mapper.SysRoleMapper;
import com.jym.car.mapper.UserMapper;
import com.jym.car.model.entity.LoginUser;
import com.jym.car.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        List<String> roleByUserId = sysRoleMapper.findRoleByUserId(user.getId());
        /*List<String> aaa = new ArrayList<>();
        for (String s : roleByUserId) {
            String a = "ROLE_" + s;
            aaa.add(a);
        }*/
        //封装成UserDetails对象返回
        return new LoginUser(user,roleByUserId);
    }
}

