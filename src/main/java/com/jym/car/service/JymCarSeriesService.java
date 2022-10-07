package com.jym.car.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jym.car.model.entity.JymCarSeries;

import java.util.List;

/**
 * (JymCarSeries)表服务接口
 *
 * @author makejava
 * @since 2022-09-24 16:49:13
 */
public interface JymCarSeriesService extends IService<JymCarSeries> {

    /**
     * 根据id查询车系
     * @param id
     * @return
     */
    JymCarSeries getSeriesById(Integer id);

    /**
     * 新增车系
     * @param jymCarSeries
     * @return
     */
    JymCarSeries saveSeries(JymCarSeries jymCarSeries);

    /**
     * 修改车系
     * @param jymCarSeries
     * @return
     */
    void updateSeries(JymCarSeries jymCarSeries);

    /**
     * 根据id批量删除数据
     * @param idList
     */
    void deleteSeriesByIds(List<Integer> idList);
}
