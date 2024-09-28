package com.zouzanyan.zanmanager.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zouzanyan.zanmanager.entity.Supplier;
import com.zouzanyan.zanmanager.service.SupplierService;
import com.zouzanyan.zanmanager.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-07-15
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/list")
    public PageVO list(@RequestParam Long page, @RequestParam Long size) {
        Page<Supplier> supplierPage = new Page<>(page, size);
        Page<Supplier> resultPage = this.supplierService.page(supplierPage);
        return new PageVO(resultPage.getRecords(), resultPage.getSize(), resultPage.getTotal());
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Supplier supplier) {
        return this.supplierService.save(supplier);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Supplier supplier) {
        return this.supplierService.updateById(supplier);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id) {
        return this.supplierService.removeById(id);
    }

}

