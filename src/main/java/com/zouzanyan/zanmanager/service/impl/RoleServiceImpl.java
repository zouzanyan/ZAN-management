package com.zouzanyan.zanmanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zouzanyan.zanmanager.entity.Role;
import com.zouzanyan.zanmanager.mapper.RoleMapper;
import com.zouzanyan.zanmanager.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
