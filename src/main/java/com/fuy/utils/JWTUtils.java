package com.fuy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jdk.nashorn.internal.parser.Token;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    private final static String TOKEN = "token!Q#U(^JKD";


    /**
     * 生成Token
     * @param map 传入payload
     * @return 返回token
     */
    public static String getToken(Map<String,String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,200);
        builder.withExpiresAt(instance.getTime());

        return builder.sign(Algorithm.HMAC256(TOKEN)).toString();
    }


    /**
     * 验证token
     * @param token
     */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }


    /**
     * 获取token中的payload
     * @param token
     * @return
     */
    public static DecodedJWT getToken(String token){
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }


}
