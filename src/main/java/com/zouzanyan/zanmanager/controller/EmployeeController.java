package com.zouzanyan.zanmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zouzanyan.zanmanager.entity.Employee;
import com.zouzanyan.zanmanager.entity.RoleEmployee;
import com.zouzanyan.zanmanager.service.EmployeeService;
import com.zouzanyan.zanmanager.service.RoleEmployeeService;
import com.zouzanyan.zanmanager.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-07-15
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleEmployeeService roleEmployeeService;

    @GetMapping("/list")
    public PageVO list(Long page, Long size) {
        Page<Employee> employeePage = new Page<>(page, size);
        Page<Employee> resultPage = this.employeeService.page(employeePage);
        PageVO pageVO = new PageVO(
                resultPage.getRecords(),
                resultPage.getSize(),
                resultPage.getTotal()
        );
        return pageVO;
    }

    @GetMapping("/{id}")
    public Employee getOne(@PathVariable Integer id) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_id", id);
        Employee one = employeeService.getOne(queryWrapper);
        Employee employee = new Employee();
        employee.setEmployeeId(one.getEmployeeId());
        employee.setEmployeeName(one.getEmployeeName());
        return employee;
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Employee employee) {
        // 默认密码1234
        employee.setPassword("1234");
        employee.setStatus(1);
        return this.employeeService.save(employee);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Employee employee) {
        return this.employeeService.updateById(employee);
    }

    @DeleteMapping("/delete")
    @Transactional
    public boolean delete(Integer id) {
        QueryWrapper<RoleEmployee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_id", id);
        this.roleEmployeeService.remove(queryWrapper);
        return this.employeeService.removeById(id);
    }
}

