package com.hellen.base.interceptor;

import com.hellen.base.util.UserUtil;
import com.hellen.entity.manangement.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS"))
            return true;
        User user = UserUtil.getUser();
        if (user == null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin","http://localhost:5173");
            response.setHeader("Access-Control-Allow-Headers","x-user-id");
            response.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS");
            try(PrintWriter writer = response.getWriter()){
                writer.write("{\"code\":\"401\",\"message\":\"用户未登录，请先登录\"}");
                writer.flush();
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
