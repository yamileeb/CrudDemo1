package com.jym.car.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jym.car.mapper.SysRoleMapper;
import com.jym.car.model.entity.SysRole;
import com.jym.car.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2022-10-06 15:18:18
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

}
