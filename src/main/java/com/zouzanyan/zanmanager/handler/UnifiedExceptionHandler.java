package com.zouzanyan.zanmanager.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UnifiedExceptionHandler {

    @ExceptionHandler(value = AuthorityException.class)
    public Map handlerException(Exception e){
        Map map = new HashMap();
        map.put("code", -1);
        map.put("msg",e.getMessage());
        return map;
    }

}
