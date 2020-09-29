package com.fuy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashMap;

public class JWTTest {

    @Test
    public void testJWT(){
        HashMap<String, Object> headerMap = new HashMap<>();

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR,1);//20秒

        String token = JWT.create()
                .withHeader(headerMap)//header
                .withClaim("userId", 20)
                .withClaim("userName", "testName")
                .withExpiresAt(instance.getTime())//设置过期时间
                .sign(Algorithm.HMAC256("!kl$A^%^*"));//签名  (密钥/盐)


        System.out.println(token);
    }

    @Test
    public void testResove(){
        //创建验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!kl$A^%^*")).build();

        DecodedJWT verify = jwtVerifier
                .verify(
"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6InRlc3ROYW1lIiwiZXhwIjoxNjAxMjY2ODAwLCJ1c2VySWQiOjIwfQ.iTK0t58bAo50033XRn2EwfQBRMAc3HwXacpgUnvYUj8"
                );


        System.out.println(verify.getHeader());
        System.out.println(verify.getPayload());
        System.out.println(verify.getSignature());
        System.out.println(verify.getToken());

        System.out.println(verify.getClaim("userName").asString());
        System.out.println(verify.getClaim("userId").asInt());

        System.out.println(verify.getExpiresAt().toString());


    }
}
