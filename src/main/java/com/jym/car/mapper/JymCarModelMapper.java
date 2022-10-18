package com.jym.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jym.car.model.entity.JymCarModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (JymCarModel)表数据库访问层
 *
 * @author lb
 * @since 2022-09-26 09:15:10
 */
@Mapper
public interface JymCarModelMapper extends BaseMapper<JymCarModel> {


    Page<JymCarModel> getModelsByBrandId(Page<JymCarModel> page,@Param("brandId") Integer brandId);

    List<JymCarModel> findByModelName(@Param("modelName") String s);
}
