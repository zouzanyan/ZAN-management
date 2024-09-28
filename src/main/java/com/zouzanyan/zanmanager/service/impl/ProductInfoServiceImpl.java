package com.zouzanyan.zanmanager.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zouzanyan.zanmanager.entity.ProductInfo;
import com.zouzanyan.zanmanager.mapper.ProductInfoMapper;
import com.zouzanyan.zanmanager.service.ProductInfoService;
import org.springframework.stereotype.Service;


@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

}
