package com.zouzanyan.zanmanager.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageVO {
    private List content;
    private Long size;
    private Long total;
}
