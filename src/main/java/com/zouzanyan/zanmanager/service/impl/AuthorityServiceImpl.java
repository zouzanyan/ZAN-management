package com.zouzanyan.zanmanager.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zouzanyan.zanmanager.entity.Authority;
import com.zouzanyan.zanmanager.mapper.AuthorityMapper;
import com.zouzanyan.zanmanager.service.AuthorityService;
import org.springframework.stereotype.Service;


@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {

}
