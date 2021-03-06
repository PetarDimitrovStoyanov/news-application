package com.newsApplicationMicroservice.userMicroservice.interceptor;

import com.newsApplicationMicroservice.userMicroservice.exceptions.MicroserviceKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class KeyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String key = request.getHeader("key");

        if(!key.equals(System.getenv("microservice.key"))){
            throw new MicroserviceKeyException();
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
