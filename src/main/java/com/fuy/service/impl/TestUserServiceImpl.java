package com.fuy.service.impl;

import com.fuy.dao.TestUserMapper;
import com.fuy.pojo.TestUser;
import com.fuy.service.TestUserService;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestUserServiceImpl implements TestUserService {
    @Autowired
    private TestUserMapper testUserMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public TestUser login(TestUser testUser) {
        TestUser login = testUserMapper.login(testUser);
        if(login != null){
            return login;
        }
        throw new RuntimeException("登陆失败");
    }
}
