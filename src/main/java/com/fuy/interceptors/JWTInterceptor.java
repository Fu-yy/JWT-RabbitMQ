package com.fuy.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuy.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中的令牌
        String token = request.getHeader("token");

        Map<String , Object> map = new HashMap<>();
        try {
            JWTUtils.verify(token);

//            map.put("state",true);
//            map.put("msg","成功");
            return true;
        } catch (SignatureVerificationException e) {
            map.put("msg","SignatureVerificationException 异常");
            e.printStackTrace();
        }catch (TokenExpiredException e){
            map.put("msg","TokenExpiredException 异常");
            e.printStackTrace();
        }catch (AlgorithmMismatchException e){

            map.put("msg","AlgorithmMismatchException 异常");
            e.printStackTrace();
        }catch (Exception e){

            map.put("msg","Exception 异常");
            e.printStackTrace();
        }
        map.put("state",false);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
