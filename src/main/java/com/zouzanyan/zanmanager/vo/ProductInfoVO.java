package com.zouzanyan.zanmanager.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoVO {
    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private Integer categoryId;
    private String categoryName;
}
