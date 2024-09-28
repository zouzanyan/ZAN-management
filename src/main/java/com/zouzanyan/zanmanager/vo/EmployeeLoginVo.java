package com.zouzanyan.zanmanager.vo;

import lombok.Data;

@Data
public class EmployeeLoginVo {
    private Integer employeeId;
    private String employeeName;
    private String roleId;
    private String roleName;
    private String authorityControllerList;
}
