package com.zouzanyan.zanmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zouzanyan.zanmanager.entity.RoleEmployee;
import com.zouzanyan.zanmanager.service.RoleEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-07-15
 */
@RestController
@RequestMapping("/roleEmployee")
public class RoleEmployeeController {

    @Autowired
    private RoleEmployeeService roleEmployeeService;

    @PutMapping("/updateRole")
    public boolean updateRole(@RequestBody RoleEmployee roleEmployee){
        QueryWrapper<RoleEmployee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_id", roleEmployee.getEmployeeId());
        this.roleEmployeeService.remove(queryWrapper);
        return this.roleEmployeeService.save(roleEmployee);
    }

    @GetMapping("/getRoleByEmployeeId")
    public RoleEmployee getRoleByEmployeeId(Integer id){
        QueryWrapper<RoleEmployee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_id", id);
        return this.roleEmployeeService.getOne(queryWrapper);
    }
}

