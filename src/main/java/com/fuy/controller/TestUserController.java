package com.fuy.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fuy.pojo.TestUser;
import com.fuy.service.TestUserService;
import com.fuy.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TestUserController {

    @Autowired
    private TestUserService testUserService;

    @GetMapping("/user/login")
    public Map<String,Object> login(String name,String pwd){


        Map<String, Object> map = new HashMap<>();

        TestUser testUser = new TestUser();
        testUser.setName(name);
        testUser.setPwd(pwd);
        try {
            TestUser login = testUserService.login(testUser);
//          生成JWT令牌
            Map<String, String> payLoad = new HashMap<>();
            payLoad.put("id",login.getId()+"");
            payLoad.put("name",login.getName());
            payLoad.put("pwd",login.getPwd());
            String token = JWTUtils.getToken(payLoad);

            map.put("state",true);
            map.put("token",token);//响应token
            map.put("resultCode",200);

        } catch (Exception e) {
            map.put("state",false);
            map.put("resultCode",200);

        }
        return map;

    }

    @PostMapping("/user/test")
    public Map<String,Object> test(String token){
        log.info("当前token为:[{}]",token);
        Map<String , Object> map = new HashMap<>();
//        try {
//            JWTUtils.verify(token);
//
            map.put("state",true);
            map.put("msg","成功");
//            return map;
//        } catch (SignatureVerificationException e) {
//            map.put("state",false);
//            map.put("msg","SignatureVerificationException 异常");
//            e.printStackTrace();
//        }catch (TokenExpiredException e){
//            map.put("state",false);
//            map.put("msg","TokenExpiredException 异常");
//            e.printStackTrace();
//        }catch (AlgorithmMismatchException e){
//            map.put("state",false);
//            map.put("msg","AlgorithmMismatchException 异常");
//            e.printStackTrace();
//        }catch (Exception e){
//            map.put("state",false);
//            map.put("msg","Exception 异常");
//            e.printStackTrace();
//        }
        return map;
    }
}
