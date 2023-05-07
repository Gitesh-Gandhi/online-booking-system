package com.ani.bookingSystem.util;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Component;


@Component
public class DynamicMapper {

    public <T, U> U convertor(T entity, U dto) {
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
   
}  