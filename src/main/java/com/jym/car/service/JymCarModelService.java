package com.jym.car.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jym.car.model.entity.JymCarModel;
import com.jym.car.model.entity.JymCarSeries;

import java.util.List;

/**
 * (JymCarModel)表服务接口
 *
 * @author lb
 * @since 2022-09-26 09:15:10
 */
public interface JymCarModelService extends IService<JymCarModel> {


    /**
     * 根据id获取车系信息
     * @param id
     * @return
     */
    JymCarModel getModelById(Integer id);

    /**
     * 新增车型
     * @param jymCarModel
     * @return
     */
    JymCarModel saveModel(JymCarModel jymCarModel);

    /**
     * 修改车型数据
     * @param jymCarModel
     */
    void updateModel(JymCarModel jymCarModel);

    /**
     * 批量删除车型信息
     * @param idList
     */
    void deleteModelByIds(List<Integer> idList);

    /**
     * 根据品牌id获取车型
     * @param page
     * @param brandId
     * @return
     */

    Page<JymCarModel> getModelsByBrandId(Page<JymCarModel> page, Integer brandId);
}
