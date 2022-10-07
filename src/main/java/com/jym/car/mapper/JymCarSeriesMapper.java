package com.jym.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jym.car.model.entity.JymCarSeries;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (JymCarSeries)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-24 16:49:12
 */
public interface JymCarSeriesMapper extends BaseMapper<JymCarSeries> {

    /**
     * 根据id批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") List<Integer> ids);
}
