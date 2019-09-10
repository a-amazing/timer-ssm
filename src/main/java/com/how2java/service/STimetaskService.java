package com.how2java.service;

import com.how2java.pojo.WxTimetask;
import com.how2java.pojo.WxTimetaskExample;

import java.util.List;

/**
 * @author:wangyi
 * @Date:2019/9/9
 */
public interface STimetaskService {
    List<WxTimetask> selectByExample(WxTimetaskExample example);
}
