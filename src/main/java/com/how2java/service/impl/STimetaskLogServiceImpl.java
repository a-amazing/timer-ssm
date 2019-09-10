package com.how2java.service.impl;

import com.how2java.mapper.WxTimetaskLogMapper;
import com.how2java.pojo.WxTimetaskLog;
import com.how2java.service.STimetaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:wangyi
 * @Date:2019/9/9
 */
@Service
public class STimetaskLogServiceImpl implements STimetaskLogService {
    @Autowired
    private WxTimetaskLogMapper timetaskLogMapper;

    @Override
    public int insertSelective(WxTimetaskLog tlog) {
       return timetaskLogMapper.insert(tlog);
    }
}
