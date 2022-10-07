package com.jym.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jym.car.model.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-06 15:18:18
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<String> findRoleByUserId(Long userId);
}
