package com.fuy.service;

import com.fuy.pojo.TestUser;
import org.springframework.stereotype.Service;


public interface TestUserService {
    TestUser login(TestUser testUser);//登录接口

}
