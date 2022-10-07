package com.jym.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jym.car.common.Verif;
import com.jym.car.exception.MyException;
import com.jym.car.mapper.JymCarSeriesMapper;

import com.jym.car.model.entity.JymCarBrand;
import com.jym.car.model.entity.JymCarSeries;
import com.jym.car.service.JymCarBrandService;
import com.jym.car.service.JymCarSeriesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (JymCarSeries)表服务实现类
 *
 * @author makejava
 * @since 2022-09-24 16:49:13
 */
@Service("jymCarSeriesService")
public class JymCarSeriesServiceImpl extends ServiceImpl<JymCarSeriesMapper, JymCarSeries> implements JymCarSeriesService {

    @Resource
    JymCarBrandService jymCarBrandService;

    @Resource
    JymCarSeriesMapper jymCarSeriesMapper;

    /**
     * 根据id查询车系
     *
     * @param id
     * @return
     */
    @Override
    public JymCarSeries getSeriesById(Integer id) {
        verificationId(id);
        return this.getById(id);

    }

    /**
     * 新增车系
     * @param jymCarSeries
     * @return
     */
    @Override
    public JymCarSeries saveSeries(JymCarSeries jymCarSeries) {
        //验证基本参数
        verification(jymCarSeries);
        save(jymCarSeries);
        return jymCarSeries;
    }

    /**
     * 修改车系
     *
     * @param jymCarSeries
     * @return
     */
    @Override
    public void updateSeries(JymCarSeries jymCarSeries) {
        //验证基本参数
        verification(jymCarSeries);
        //验证id是否存在
        verificationId(jymCarSeries.getSeriesid());
        updateById(jymCarSeries);
    }

    /**
     * 根据id批量删除数据
     *
     * @param idList
     */
    @Override
    public void deleteSeriesByIds(List<Integer> idList) {
        for (Integer integer : idList) {
            //验证id是否存在
            verificationId(integer);
        }
        jymCarSeriesMapper.deleteByIds(idList);
    }

    /**
     * 验证id是否存在
     * @param id
     */
    public void verificationId(Integer id){
        int count = count(new LambdaQueryWrapper<JymCarSeries>()
                .eq(JymCarSeries::getSeriesid, id));
        Verif.varIsZero(count,"id不存在");
    }

    /**
     * 验证基本参数
     * @param series
     */
    public void verification(JymCarSeries series){
        Verif.varIsNull(series.getSeriesname());
        Verif.varIsNull(series.getIsuse());
        Verif.varIsNull(series.getIsnewenergy());
        int count = jymCarBrandService.count(new LambdaQueryWrapper<JymCarBrand>()
                .eq(JymCarBrand::getBrandid, series.getBrandid()));
        Verif.varIsZero(count,"品牌id不存在");
    }

}
