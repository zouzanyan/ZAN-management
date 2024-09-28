package com.zouzanyan.zanmanager.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zouzanyan.zanmanager.entity.RoleAuthority;

import java.util.List;

public interface RoleAuthorityService extends IService<RoleAuthority> {

    public List<String> getAuthorityFromEmployeeId(Integer employeeId);

}
