package com.jym.car.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 日志表
 * @author crush
 */
@Data
public class JymLog implements Serializable {
    private static final long serialVersionUID = 7925874058046995566L;
    @TableId(type = IdType.INPUT)
    private String id;
    /*** 用户id  */
    private String userId;
    /** * 用户名称*/
    private String username;
    /** * 登录ip */
    private String loginIp;
    /** * 操作类型(0登录、1查询、2修改、3新增、4删除)  */
    private int type;
    /** *  url*/
    private String uri;
    /**  * 操作 */
    private String operation;
    /** * 操作时间*/
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    /*** 备注*/
    private String remark;
    /*** 修改时间*/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;
}
