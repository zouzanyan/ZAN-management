package com.zouzanyan.zanmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "employee_id", type = IdType.AUTO)
    private Integer employeeId;

    private String employeeName;

    private String sex;

    private String phone;

    private String email;

    private String address;

    private String remark;

    private String password;

    private Integer status;


}
