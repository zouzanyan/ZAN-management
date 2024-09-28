package com.zouzanyan.zanmanager.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zouzanyan.zanmanager.entity.Employee;
import com.zouzanyan.zanmanager.mapper.EmployeeMapper;
import com.zouzanyan.zanmanager.service.EmployeeService;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
