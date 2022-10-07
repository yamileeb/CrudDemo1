package com.jym.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jym.car.common.Verif;
import com.jym.car.mapper.JymCarBrandMapper;
import com.jym.car.mapper.JymCarSeriesMapper;
import com.jym.car.model.entity.JymCarBrand;
import com.jym.car.model.entity.JymCarModel;
import com.jym.car.mapper.JymCarModelMapper;
import com.jym.car.model.entity.JymCarSeries;
import com.jym.car.service.JymCarBrandService;
import com.jym.car.service.JymCarModelService;
import com.jym.car.service.JymCarSeriesService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.Resource;
import java.util.List;

/**
 * (JymCarModel)表服务实现类
 *
 * @author lb
 * @since 2022-09-26 09:15:11
 */
@Service("jymCarModelService")
public class JymCarModelServiceImpl extends ServiceImpl<JymCarModelMapper, JymCarModel> implements JymCarModelService {

    @Resource
    JymCarSeriesService jymCarSeriesService;

    @Resource
    JymCarModelMapper jymCarModelMapper;

    @Resource
    JymCarBrandService jymCarBrandService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 根据id获取车系信息
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(cacheNames = "model" , key =  "#id")
    public JymCarModel getModelById(Integer id) {
        //验证id是否存在
        verificationId(id);
        return getById(id);
    }

    /**
     * 新增车型
     * @param jymCarModel
     * @return
     */
    @Override
    public JymCarModel saveModel(JymCarModel jymCarModel) {
        verification(jymCarModel);
        save(jymCarModel);
        return jymCarModel;
    }

    /**
     * 修改车型数据
     *
     * @param jymCarModel
     */
    @Override
    @CacheEvict(cacheNames = "model" , key =  "#jymCarModel.modelid")
    public void updateModel(JymCarModel jymCarModel) {
        verificationId(jymCarModel.getModelid());
        verification(jymCarModel);
        updateById(jymCarModel);
    }

    /**
     * 批量删除车型信息
     *
     * @param idList
     */
    @Override
    public void deleteModelByIds(List<Integer> idList) {
        for (Integer integer : idList) {
            stringRedisTemplate.delete("model::" + integer);
        }
        for (Integer integer : idList) {
            verificationId(integer);
        }
        removeByIds(idList);
    }

    /**
     * 根据品牌id获取车型
     *
     * @param page
     * @param brandId
     * @return
     */
    @Override
    public Page<JymCarModel> getModelsByBrandId(Page<JymCarModel> page, Integer brandId) {
        verificationBrandId(brandId);
        Page<JymCarModel> page1 =  jymCarModelMapper.getModelsByBrandId(page,brandId);
        return page1;
    }

    /**
     * 验证id是否存在
     * @param id
     */
    public void verificationId(Integer id){
        int count = count(new LambdaQueryWrapper<JymCarModel>()
                .eq(JymCarModel::getModelid, id));
        Verif.varIsZero(count,"id不存在");
    }



    /**
     * 验证基本参数
     * @param model
     */
    public void verification(JymCarModel model){
        Verif.varIsNull(model.getModelname());
        Verif.varIsNull(model.getYear());
        int count = jymCarSeriesService.count(new LambdaQueryWrapper<JymCarSeries>()
                .eq(JymCarSeries::getSeriesid, model.getSeriesid()));
        Verif.varIsZero(count,"车系id不存在");
    }

    public void verificationBrandId(Integer id){
        int count = jymCarBrandService.count(new LambdaQueryWrapper<JymCarBrand>()
                .eq(JymCarBrand::getBrandid, id));
        Verif.varIsZero(count,"品牌id不存在");
    }
}
