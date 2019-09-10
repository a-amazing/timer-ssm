package com.how2java.mapper;


import com.how2java.pojo.WxTimetask;
import com.how2java.pojo.WxTimetaskExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxTimetaskMapper {
    int countByExample(WxTimetaskExample example);

    int deleteByExample(WxTimetaskExample example);

    int insert(WxTimetask record);

    int insertSelective(WxTimetask record);

    List<WxTimetask> selectByExample(WxTimetaskExample example);

    int updateByExampleSelective(@Param("record") WxTimetask record, @Param("example") WxTimetaskExample example);

    int updateByExample(@Param("record") WxTimetask record, @Param("example") WxTimetaskExample example);
}