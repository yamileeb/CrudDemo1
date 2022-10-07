package com.jym.car.model.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (JymCarBrand)实体类
 *
 * @author lb
 * @since 2022-09-24 15:06:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JymCarBrand implements Serializable {
    private static final long serialVersionUID = -52635013571391007L;

    @TableId
    @ExcelIgnore
    private Integer brandid;

    @TableField(condition = SqlCondition.LIKE)
    @ExcelProperty("品牌名")
    private String brandname;

    @ExcelProperty("首字母")
    private String firstletter;

    @ExcelProperty("品牌logo")
    private String brandlogo;

    /**
    * 是否可用：1（可用）；2（不可用）；
    */
    @ExcelIgnore
    private Integer isuse;

    /**
    * 备注
    */
    @ExcelProperty("备注")
    private String remark;

    /**
    * 创建者
    */
    @ExcelProperty("创建者")
    private Integer creater;

    /**
    * 创建时间
    */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ExcelProperty("创建时间")
    private Date createtime;

    /**
    * 更新者
    */
    @ExcelIgnore
    private Integer updater;

    /**
    * 更新时间
    */
    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    /**
    * 逻辑删除标识（auto：正常；delete：删除）
    */
    @ExcelIgnore
    @TableLogic(value = "auto",delval = "delete")
    private String dflag;

}
