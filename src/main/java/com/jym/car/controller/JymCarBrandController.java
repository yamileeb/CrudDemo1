package com.jym.car.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jym.car.annotation.MyLog;
import com.jym.car.model.entity.JymCarBrand;
import com.jym.car.model.entity.JymCarSeries;
import com.jym.car.model.result.Result;
import com.jym.car.service.JymCarBrandService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * (JymCarBrand)表控制层
 *
 * @author lb
 * @since 2022-09-24 15:06:31
 */
@RestController
@RequestMapping("jymCarBrand")
public class JymCarBrandController {
    /**
     * 服务对象
     */
    @Resource
    private JymCarBrandService jymCarBrandService;


    /**
     * 获取所有品牌信息
     * @return
     */
    @GetMapping
    @MyLog(operation = "分页条件查询品牌信息",type = 1)
    @PreAuthorize("hasAnyAuthority('user')")
    public Result selectAll(Page<JymCarBrand> page, JymCarBrand jymCarBrand) {
        return Result.ok(this.jymCarBrandService.page(page, new QueryWrapper<>(jymCarBrand)));
    }

    /**
     * 根据品牌id查询品牌信息
     * @param id
     * @return
     */
    @GetMapping("getBrandById/{id}")
    @MyLog(operation = "根据id查询品牌信息",type = 1)
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public Result<JymCarBrand> getBrandById(@PathVariable("id") Integer id){
        JymCarBrand brand = jymCarBrandService.getBrandById(id);
        return Result.ok(brand);
    }

    /**
     * 修改品牌信息
     * @param brand
     * @return
     */
    @PostMapping("updateBrand")
    @CacheEvict(cacheNames = "brandById", key = "#brand.brandid")
    @MyLog(operation = "修改品牌信息",type = 3)
    @PreAuthorize("hasAnyAuthority('admin')")
    public Result<String> updateBrand(@RequestBody JymCarBrand brand){
        jymCarBrandService.updateBrand(brand);
        return Result.ok(null);
    }

    /**
     * 根据id删除品牌
     * @param id
     * @return
     */
    @PostMapping("deleteBrandById/{id}")
    @CacheEvict(cacheNames = "brandById", key = "#id")
    @MyLog(operation = "删除品牌",type = 4)
    @PreAuthorize("hasAnyAuthority('admin')")
    public Result<String> deleteBrandById(@PathVariable Integer id){
        jymCarBrandService.deleteBrandById(id);
        return Result.ok(null);
    }

    /**
     * 新增品牌
     * @param brand
     * @return
     */
    @PostMapping("saveBrand")
    @MyLog(operation = "新增品牌",type = 3)
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public Result<JymCarBrand> saveBrand(@RequestBody JymCarBrand brand){
        return Result.ok(jymCarBrandService.saveBrand(brand));
    }


    @GetMapping("/export")
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public void export(HttpServletResponse response) throws Exception {
        List<JymCarBrand> list = jymCarBrandService.list();
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("品牌信息", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), JymCarBrand.class).sheet("模板").doWrite(list);

    }





}
