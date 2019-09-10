package com.how2java.mapper;


import com.how2java.pojo.WxTimetaskLog;

public interface WxTimetaskLogMapper {
    int insert(WxTimetaskLog record);

    int insertSelective(WxTimetaskLog record);
}