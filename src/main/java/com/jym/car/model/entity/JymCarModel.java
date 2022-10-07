package com.jym.car.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (JymCarModel)实体类
 *
 * @author lb
 * @since 2022-09-26 09:15:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JymCarModel implements Serializable {
    private static final long serialVersionUID = 934597664466585351L;

    @TableId
    private Integer modelid;

    private Integer seriesid;

    @TableField(condition = SqlCondition.LIKE)
    private String modelname;

    private String guideprice;

    private Integer year;

    private String configuration;

    /**
    * 是否可用：1（可用）；2（不可用）；
    */
    private Integer isuse;

    /**
    * 备注
    */
    private String remark;

    /**
    * 创建者
    */
    private Integer creater;

    /**
    * 创建时间
    */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;

    /**
    * 更新者
    */
    private Integer updater;

    /**
    * 更新时间
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    /**
    * 逻辑删除标识（auto：正常；delete：删除）
    */
    @TableLogic(value = "auto",delval = "delete")
    private String dflag;

}
