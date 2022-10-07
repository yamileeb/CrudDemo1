package com.jym.car.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (JymCarSeries)表实体类
 *
 * @author makejava
 * @since 2022-09-24 16:49:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JymCarSeries extends Model<JymCarSeries> {

    @TableId
    private Integer seriesid;

    private Integer brandid;

    @TableField(condition = SqlCondition.LIKE)
    private String seriesname;

    private String levelname;

    private String sgroup;

    //是否可用：1（可用）；2（不可用）；
    private Integer isuse;

    //备注
    private String remark;

    //创建者
    private Integer creater;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;

    //更新者
    private Integer updater;

    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatetime;

    //逻辑删除标识（auto：正常；delete：删除）
    @TableLogic(value = "auto", delval = "delete")
    private String dflag;

    //是否是新能源车：1（否）；2（是）；
    private Integer isnewenergy;

    //1：自主；2：合资；3：进口；4：独资
    private Integer placename;


}
