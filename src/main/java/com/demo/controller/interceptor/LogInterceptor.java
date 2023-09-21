package com.demo.controller.interceptor;

import com.alibaba.druid.util.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class LogInterceptor implements HandlerInterceptor {

    //定义常量TRACE_ID
    public static final String TRACE_ID = "TRACE_ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tid = UUID.randomUUID().toString().replace("-","");
        //可以考虑让客户端传入链路ID，但需要保证一定的复杂度和唯一性，如果没有使用默认UUID
        if (!StringUtils.isEmpty(request.getHeader("TRACE_ID"))) {
            tid = request.getHeader("TRACE_ID");
        }
        MDC.put(TRACE_ID,tid);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(TRACE_ID);
    }
}
