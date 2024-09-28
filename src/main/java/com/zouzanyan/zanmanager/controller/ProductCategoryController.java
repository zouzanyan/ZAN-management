package com.zouzanyan.zanmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zouzanyan.zanmanager.entity.ProductCategory;
import com.zouzanyan.zanmanager.entity.ProductInfo;
import com.zouzanyan.zanmanager.service.ProductCategoryService;
import com.zouzanyan.zanmanager.service.ProductInfoService;
import com.zouzanyan.zanmanager.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 类目表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-07-15
 */
@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/list")
    public PageVO list(Long page, Long size){
        Page<ProductCategory> productCategoryPage = new Page<>(page, size);
        Page<ProductCategory> resultPage = this.productCategoryService.page(productCategoryPage);
        PageVO pageVO = new PageVO(
                resultPage.getRecords(),
                resultPage.getSize(),
                resultPage.getTotal());
        return pageVO;
    }

    @PostMapping("/save")
    public boolean save(@RequestBody ProductCategory productCategory){
        return this.productCategoryService.save(productCategory);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody ProductCategory productCategory){
        return this.productCategoryService.updateById(productCategory);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public boolean delete(@PathVariable Integer id){
        //删除从表
        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", id);
        this.productInfoService.remove(queryWrapper);
        //删除主表
        return this.productCategoryService.removeById(id);
    }

    @GetMapping("/categoryList")
    public List<ProductCategory> categoryList() {
        List<ProductCategory> list = this.productCategoryService.list();
        if(list == null) {
            throw new RuntimeException("商品分类不存在");
        }
        return list;
    }

}

