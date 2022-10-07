package com.jym.car.service.impl;

import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jym.car.common.SysLogAspect;
import com.jym.car.common.Verif;
import com.jym.car.exception.MyException;
import com.jym.car.mapper.JymCarBrandMapper;
import com.jym.car.model.entity.JymCarBrand;
import com.jym.car.model.result.Result;
import com.jym.car.service.JymCarBrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (JymCarBrand)表服务实现类
 *
 * @author lb
 * @since 2022-09-24 15:06:30
 */
@Service("jymCarBrandService")
public class JymCarBrandServiceImpl extends ServiceImpl<JymCarBrandMapper,JymCarBrand> implements JymCarBrandService {

    private static final Logger log = LogManager.getLogger(JymCarBrandServiceImpl.class);

    @Resource
    JymCarBrandMapper jymCarBrandMapper;



    /**
     * 修改品牌
     * @param brand
     */
    @Override
    public void updateBrand(JymCarBrand brand) {
        //参数校验
        verification(brand);
        verificationId(brand.getBrandid());
        verificationNameIsRepetition(brand);
        //获取首字母,转大写
        String firstLetter = PinyinUtil.getFirstLetter(brand.getBrandname(), "");
        String substring = firstLetter.substring(0,1);
        brand.setFirstletter(substring.toUpperCase());
        updateById(brand);
    }

    /**
     * 根据id删除品牌
     * @param id
     */
    @Override
    public void deleteBrandById(Integer id) {
        //验证id是否合法
        Verif.varIsNull(id);
        int count = count(new LambdaQueryWrapper<JymCarBrand>()
                .eq(JymCarBrand::getBrandid, id));
        Verif.varIsZero(count,"id不存在");
        jymCarBrandMapper.removeBrandAndChild(id);
    }

    /**
     * 新增品牌
     *
     * @param brand
     * @return
     */
    @Override
    public JymCarBrand saveBrand(JymCarBrand brand) {
        verification(brand);
        //获取首字母,转大写
        String firstLetter = PinyinUtil.getFirstLetter(brand.getBrandname(), "");
        String substring = firstLetter.substring(0,1);
        brand.setFirstletter(substring.toUpperCase());
        verificationNameIsRepetition(brand);
        save(brand);
        return brand;
    }


    /**
     * 验证名称是否重复
     */
    public void verificationNameIsRepetition(JymCarBrand brand){
        //验证名称是否重复
        int count = count(new LambdaQueryWrapper<JymCarBrand>()
                .eq(JymCarBrand::getBrandname, brand.getBrandname())
                .ne(ObjectUtils.isNotEmpty(brand.getBrandid()),JymCarBrand::getBrandid,brand.getBrandid()));
        if(count > 0){
            throw new MyException("品牌名重复");
        }
    }

    @Override
    @Cacheable(cacheNames = "brandById", key = "#id")
    public JymCarBrand getBrandById(Integer id) {
        JymCarBrand brand = getOne(new LambdaQueryWrapper<JymCarBrand>()
                .eq(JymCarBrand::getBrandid, id));
        if(ObjectUtils.isEmpty(brand)){
            throw new MyException("品牌不存在");
        }
        return brand;
    }

    /**
     * 验证id是否存在
     * @param id
     */
    public void verificationId(Integer id){
        int count = count(new LambdaQueryWrapper<JymCarBrand>()
                .eq(JymCarBrand::getBrandid, id));
        Verif.varIsZero(count,"id不存在");
    }

    /**
     * 验证基本参数
     * @param brand
     */
    public void verification(JymCarBrand brand){
        Verif.varIsNull(brand.getBrandname());
        Verif.varIsNull(brand.getIsuse());
    }
}
