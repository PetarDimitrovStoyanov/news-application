package com.newsApplicationMicroservice.userMicroservice.interceptor;

import com.newsApplicationMicroservice.userMicroservice.exceptions.MicroserviceKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class KeyInterceptor implements HandlerInterceptor {
    private final static String MICROSERVICE_KEY = "1aWff2!#_-zWN4";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String key = request.getHeader("key");

        if(!key.equals(MICROSERVICE_KEY)){
            throw new MicroserviceKeyException();
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
