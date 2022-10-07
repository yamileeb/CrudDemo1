package com.jym.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jym.car.model.entity.JymCarBrand;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lb
 */
@Mapper
public interface JymCarBrandMapper extends BaseMapper<JymCarBrand> {

    void removeBrandAndChild(Integer id);
}
