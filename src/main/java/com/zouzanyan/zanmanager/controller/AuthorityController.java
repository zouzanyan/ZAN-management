package com.zouzanyan.zanmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zouzanyan.zanmanager.entity.Authority;
import com.zouzanyan.zanmanager.entity.RoleAuthority;
import com.zouzanyan.zanmanager.service.AuthorityService;
import com.zouzanyan.zanmanager.service.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RoleAuthorityService roleAuthorityService;

    /**
     * 获取权限信息数据
     */
    @GetMapping("/list")
    public List<Authority> list() {
        return this.authorityService.list();
    }


    /**
     * 根据角色id获取权限数组
     */
    @GetMapping("role/{roleId}")
    public List<String> getEmployeeControllerAuthorityList(@PathVariable String roleId) {
        QueryWrapper<RoleAuthority> roleAuthorityQueryWrapper = new QueryWrapper<>();
        roleAuthorityQueryWrapper.eq("role_id", roleId);
        List<RoleAuthority> list = roleAuthorityService.list(roleAuthorityQueryWrapper);
        ArrayList<String> strings = new ArrayList<>();
        for (RoleAuthority roleAuthority : list) {
            String controller = authorityService.getById(roleAuthority.getAuthorityId()).getController();
            strings.add(controller);
        }
        return strings;
    }

}

