package com.zouzanyan.zanmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
  @EqualsAndHashCode(callSuper = false)
    public class RoleEmployee implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "role_employee_id", type = IdType.AUTO)
      private Integer roleEmployeeId;

    private Integer employeeId;

    private Integer roleId;


}
