package com.zouzanyan.zanmanager.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zouzanyan.zanmanager.entity.Authority;
import com.zouzanyan.zanmanager.entity.RoleAuthority;
import com.zouzanyan.zanmanager.entity.RoleEmployee;
import com.zouzanyan.zanmanager.mapper.RoleAuthorityMapper;
import com.zouzanyan.zanmanager.service.AuthorityService;
import com.zouzanyan.zanmanager.service.RoleAuthorityService;
import com.zouzanyan.zanmanager.service.RoleEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleAuthorityServiceImpl extends ServiceImpl<RoleAuthorityMapper, RoleAuthority> implements RoleAuthorityService {

    @Autowired
    private RoleEmployeeService roleEmployeeService;

    @Autowired
    private AuthorityService authorityService;


    /**
     * 根据employeeId获取用户的权限列表
     */
    @Override
    public List<String> getAuthorityFromEmployeeId(Integer employeeId) {
        ArrayList<String> authorityList = new ArrayList<>();
        try {
            QueryWrapper<RoleEmployee> roleEmployeeQueryWrapper = new QueryWrapper<>();
            roleEmployeeQueryWrapper.eq("employee_id", employeeId);
            RoleEmployee one = roleEmployeeService.getOne(roleEmployeeQueryWrapper);
            Integer roleId = one.getRoleId();
            QueryWrapper<RoleAuthority> roleAuthorityQueryWrapper = new QueryWrapper<>();
            roleAuthorityQueryWrapper.eq("role_id", roleId);
            List<RoleAuthority> list = this.list(roleAuthorityQueryWrapper);
            for (RoleAuthority roleAuthority : list) {
                Integer authorityId = roleAuthority.getAuthorityId();
                Authority authority = authorityService.getById(authorityId);
                authorityList.add(authority.getController());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return authorityList;

    }

}
