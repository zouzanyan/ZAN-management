package com.zouzanyan.zanmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zouzanyan.zanmanager.entity.Employee;
import com.zouzanyan.zanmanager.service.EmployeeService;
import com.zouzanyan.zanmanager.service.RoleAuthorityService;
import com.zouzanyan.zanmanager.util.CookieUtil;
import com.zouzanyan.zanmanager.vo.EmployeeLoginVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @PostMapping("/login")
    public Map login(HttpServletResponse httpServletResponse, @RequestBody Employee employee) {
        //判断账号是否存在
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", employee.getPhone());
        Employee one = this.employeeService.getOne(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        if (one == null) {
            map.put("code", -1);
            return map;
        }
        //判断密码是否正确
        if (!one.getPassword().equals(employee.getPassword())) {
            map.put("code", -2);
            return map;
        }
        List<String> authorities = roleAuthorityService.getAuthorityFromEmployeeId(one.getEmployeeId());
        EmployeeLoginVo employeeLoginVo = new EmployeeLoginVo();
        employeeLoginVo.setAuthorityControllerList(StringUtils.join(authorities,","));
        employeeLoginVo.setEmployeeId(one.getEmployeeId());
        map.put("code", 0);
        map.put("data", employeeLoginVo);

        // employeeId 唯一用户标识
        Integer employeeId = one.getEmployeeId();
        Cookie cookie = new Cookie("userinfo", CookieUtil.signValue(employeeId.toString()));
        cookie.setMaxAge(60 * 60 * 24 * 30);
        httpServletResponse.addCookie(cookie);
        return map;

    }

}
