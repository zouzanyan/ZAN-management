package com.zouzanyan.zanmanager.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zouzanyan.zanmanager.entity.ProductInfo;
import com.zouzanyan.zanmanager.service.ProductCategoryService;
import com.zouzanyan.zanmanager.service.ProductInfoService;
import com.zouzanyan.zanmanager.vo.PageVO;
import com.zouzanyan.zanmanager.vo.ProductInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-07-15
 */
@RestController
@RequestMapping("/productInfo")
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping("/save")
    public boolean save(@RequestBody ProductInfo productInfo){
        return this.productInfoService.save(productInfo);
    }

    @GetMapping("/list")
    public PageVO list(Long page, Long size){
        Page<ProductInfo> productInfoPage = new Page<>(page, size);
        Page<ProductInfo> resultPage = this.productInfoService.page(productInfoPage);
        List<ProductInfoVO> list = new ArrayList<>();
        for (ProductInfo record : resultPage.getRecords()) {
            ProductInfoVO productInfoVO = new ProductInfoVO();
            BeanUtils.copyProperties(record, productInfoVO);
            productInfoVO.setCategoryName(this.productCategoryService.getById(record.getCategoryId()).getCategoryName());
            list.add(productInfoVO);
        }
        PageVO pageVO = new PageVO(
                list,
                resultPage.getSize(),
                resultPage.getTotal());
        return pageVO;
    }

    @PutMapping("/update")
    public boolean update(@RequestBody ProductInfo productInfo){
        return this.productInfoService.updateById(productInfo);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id){
        return this.productInfoService.removeById(id);
    }

}

