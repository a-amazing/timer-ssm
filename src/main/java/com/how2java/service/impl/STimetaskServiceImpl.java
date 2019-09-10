package com.how2java.service.impl;

import com.how2java.mapper.WxTimetaskMapper;
import com.how2java.pojo.WxTimetask;
import com.how2java.pojo.WxTimetaskExample;
import com.how2java.service.STimetaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author:wangyi
 * @Date:2019/9/9
 */
@Service
public class STimetaskServiceImpl implements STimetaskService {
    @Autowired
    private WxTimetaskMapper timetaskMapper;

    @Override
    public List<WxTimetask> selectByExample(WxTimetaskExample example) {
        return timetaskMapper.selectByExample(example);
    }
}
