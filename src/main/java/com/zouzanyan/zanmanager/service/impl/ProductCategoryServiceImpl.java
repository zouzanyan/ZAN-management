package com.zouzanyan.zanmanager.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zouzanyan.zanmanager.entity.ProductCategory;
import com.zouzanyan.zanmanager.mapper.ProductCategoryMapper;
import com.zouzanyan.zanmanager.service.ProductCategoryService;
import org.springframework.stereotype.Service;


@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

}
