package com.zouzanyan.zanmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
  @EqualsAndHashCode(callSuper = false)
    public class RoleAuthority implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "role_authority_id", type = IdType.AUTO)
      private Integer roleAuthorityId;

    private Integer authorityId;

    private Integer roleId;


}
