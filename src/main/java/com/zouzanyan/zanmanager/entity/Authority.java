package com.zouzanyan.zanmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "authority_id", type = IdType.AUTO)
    @JsonProperty("id")
    private Integer authorityId;

    @JsonProperty("label")
    private String authorityName;

    private String controller;


}
