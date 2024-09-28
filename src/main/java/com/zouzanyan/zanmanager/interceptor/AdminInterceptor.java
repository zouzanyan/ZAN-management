package com.zouzanyan.zanmanager.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zouzanyan.zanmanager.entity.Authority;
import com.zouzanyan.zanmanager.entity.Employee;
import com.zouzanyan.zanmanager.entity.RoleAuthority;
import com.zouzanyan.zanmanager.entity.RoleEmployee;
import com.zouzanyan.zanmanager.handler.AuthorityException;
import com.zouzanyan.zanmanager.service.AuthorityService;
import com.zouzanyan.zanmanager.service.RoleAuthorityService;
import com.zouzanyan.zanmanager.service.RoleEmployeeService;
import com.zouzanyan.zanmanager.util.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private RoleEmployeeService roleEmployeeService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private AuthorityService authorityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //通过所有OPTION请求
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }
        String servletPath = request.getServletPath();
        Cookie[] cookies = request.getCookies();
        String userinfoValue = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userinfo")) {
                userinfoValue = cookie.getValue();
            }
        }
        // cookie验证失败，没有或者恶意修改过
        return CookieUtil.verifySignedValue(userinfoValue);
//        String[] split1 = userinfoValue.split(":");
//        Integer employeeId = Integer.valueOf(split1[0]);
//        List<String> controllerList = roleAuthorityService.getAuthorityFromEmployeeId(employeeId);
//        if (controllerList.contains(servletPath)) {
//            return true;
//        } else {
//            throw new AuthorityException("当前用户没有操作权限！");
//        }
//    }
    }
}
