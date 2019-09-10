package com.how2java.mapper;

import com.how2java.pojo.WxUser;

public interface WxUserMapper {
    int insert(WxUser record);

    int insertSelective(WxUser record);
}