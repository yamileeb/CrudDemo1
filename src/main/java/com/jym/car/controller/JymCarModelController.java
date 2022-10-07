package com.jym.car.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jym.car.annotation.MyLog;
import com.jym.car.model.entity.JymCarModel;
import com.jym.car.model.entity.JymCarSeries;
import com.jym.car.model.result.Result;
import com.jym.car.service.JymCarModelService;
import com.jym.car.service.JymCarSeriesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (JymCarModel)表控制层
 *
 * @author lb
 * @since 2022-09-26 09:15:11
 */
@RestController
@RequestMapping("jymCarModel")
public class JymCarModelController {

    /**
     * 服务对象
     */
    @Resource
    private JymCarModelService jymCarModelService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param jymCarModel 查询实体
     * @return 所有数据
     */
    @GetMapping
    @MyLog(operation = "分页条件查询车型信息",type = 1)
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public Result selectAll(Page<JymCarModel> page, JymCarModel jymCarModel) {
        return Result.ok(this.jymCarModelService.page(page, new QueryWrapper<>(jymCarModel)));
    }

    /**
     * 根据品牌id获取车型
     * @param page
     * @param brandId
     * @return
     */
    @GetMapping("getModelsByBrandId")
    @MyLog(operation = "根据品牌id查询品牌信息",type = 1)
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public Result getModelsByBrandId(Page<JymCarModel> page, @RequestParam("brandId") Integer brandId) {
        Page<JymCarModel> page1 = jymCarModelService.getModelsByBrandId(page, brandId);
        return Result.ok(page1);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getModelById/{id}")
    @MyLog(operation = "根据品牌id查询车型信息",type = 1)
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public Result<JymCarModel> getModelById(@PathVariable("id") Integer id) {
        JymCarModel model = jymCarModelService.getModelById(id);
        return Result.ok(model);
    }

    /**
     * 新增车型
     * @param jymCarModel
     * @return
     */
    @PostMapping("saveModel")
    @MyLog(operation = "新增车型信息",type = 3)
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public Result<JymCarModel> saveModel(@RequestBody JymCarModel jymCarModel) {
        return Result.ok(jymCarModelService.saveModel(jymCarModel));

    }

    /**
     * 修改车型数据
     * @param jymCarModel
     * @return
     */
    @PostMapping("updateModel")
    @MyLog(operation = "修改车型信息",type = 2)
    @PreAuthorize("hasAnyAuthority('admin')")
    public Result updateModel(@RequestBody JymCarModel jymCarModel) {
        jymCarModelService.updateModel(jymCarModel);
        return Result.ok(null);
    }

    /**
     * 删除车型信息
     * @param idList
     * @return
     */
    @PostMapping("deleteModel")
    @MyLog(operation = "批量删除车型信息",type = 4)
    @PreAuthorize("hasAnyAuthority('admin')")
    public Result deleteModel(@RequestBody List<Integer> idList) {
        jymCarModelService.deleteModelByIds(idList);
        return Result.ok(null);
    }



}
