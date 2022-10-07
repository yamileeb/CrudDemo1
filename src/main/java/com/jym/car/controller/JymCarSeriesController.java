package com.jym.car.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jym.car.annotation.MyLog;
import com.jym.car.model.entity.JymCarSeries;
import com.jym.car.model.result.Result;
import com.jym.car.service.JymCarSeriesService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (JymCarSeries)表控制层
 *
 * @author makejava
 * @since 2022-09-24 16:49:14
 */
@RestController
@RequestMapping("jymCarSeries")
public class JymCarSeriesController {

    /**
     * 服务对象
     */
    @Resource
    private JymCarSeriesService jymCarSeriesService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param jymCarSeries 查询实体
     * @return 所有数据
     */
    @GetMapping
    @MyLog(operation = "分页条件查询车系信息",type = 1)
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public Result selectAll(Page<JymCarSeries> page, JymCarSeries jymCarSeries) {
        return Result.ok(this.jymCarSeriesService.page(page, new QueryWrapper<>(jymCarSeries)));
    }

    /**
     * 新增车系
     *
     * @param jymCarSeries 实体对象
     * @return 新增结果
     */
    @PostMapping("saveSeries")
    @MyLog(operation = "新增车系信息",type = 3)
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public Result<JymCarSeries> saveSeries(@RequestBody JymCarSeries jymCarSeries) {
        return Result.ok(jymCarSeriesService.saveSeries(jymCarSeries));

    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getSeriesById/{id}")
    @MyLog(operation = "通过车系id查询车系信息",type = 1)
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public Result<JymCarSeries> getSeriesById(@PathVariable("id") Integer id) {
        JymCarSeries series = jymCarSeriesService.getSeriesById(id);
        return Result.ok(series);
    }

    /**
     * 修改数据
     *
     * @param jymCarSeries 实体对象
     * @return 修改结果
     */
    @PostMapping("updateSeries")
    @MyLog(operation = "修改车系信息",type = 2)
    @PreAuthorize("hasAnyAuthority('admin')")
    public Result updateSeries(@RequestBody JymCarSeries jymCarSeries) {
        jymCarSeriesService.updateSeries(jymCarSeries);
        return Result.ok(null);
    }

    /**
     * 删除数据
     *
     * @param idList 主键集合
     * @return 删除结果
     */
    @PostMapping("deleteSeries")
    @MyLog(operation = "删除车系信息",type = 4)
    @PreAuthorize("hasAnyAuthority('admin')")
    public Result deleteSeries(@RequestBody List<Integer> idList) {
        jymCarSeriesService.deleteSeriesByIds(idList);
        return Result.ok(null);
    }
}
