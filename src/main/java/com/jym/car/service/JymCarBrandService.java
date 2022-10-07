package com.jym.car.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jym.car.model.entity.JymCarBrand;

/**
 * (JymCarBrand)表服务接口
 *
 * @author lb
 * @since 2022-09-24 15:06:30
 */
public interface JymCarBrandService extends IService<JymCarBrand> {

    /**
     * 修改品牌
     * @param brand
     */
    void updateBrand(JymCarBrand brand);

    /**
     * 根据id删除品牌
     * @param id
     */
    void deleteBrandById(Integer id);

    /**
     * 新增品牌
     * @param brand
     * @return
     */
    JymCarBrand saveBrand(JymCarBrand brand);

    JymCarBrand getBrandById(Integer id);
}
