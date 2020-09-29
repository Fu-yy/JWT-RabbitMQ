package com.fuy.dao;


import com.fuy.pojo.TestUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
public interface TestUserMapper {

    TestUser login(TestUser testUser);//登录接口
}
