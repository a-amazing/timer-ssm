package com.how2java.service.impl;

import com.how2java.mapper.WxUserMapper;
import com.how2java.pojo.WxUser;
import com.how2java.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:wangyi
 * @Date:2019/9/10
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private WxUserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void print() {
        System.out.println("这是一个测试方法!");
        WxUser wxUser = new WxUser();
        wxUser.setUsername("wangyi");
        wxUser.setMobilePhone("13579256479");
        userMapper.insertSelective(wxUser);

        int i = 1/0;
    }
}
