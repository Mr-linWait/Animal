package com.hellen.base.util;

import com.hellen.entity.manangement.User;
import com.hellen.server.user.UserController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.ConcurrentHashMap;

public class UserUtil {

    private final static ConcurrentHashMap<Long,User> USER_INFO_LOGIN=new ConcurrentHashMap();

    /**
     * 获取当前登录用户
     * @return
     */
    public static User getUser() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String header = request.getHeader("X-User-ID");
        if (StringUtils.hasText(header))
            return USER_INFO_LOGIN.get(Long.valueOf(header));
        else
            return null;
    }

    public static void setActiveUser(Long userId,User user){
        USER_INFO_LOGIN.put(userId,user);
    }

    public static boolean reomveUser() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String header = request.getHeader("X-User-ID");
        User user ;
        if (StringUtils.hasText(header))
            user= USER_INFO_LOGIN.remove(Long.valueOf(header));
        else
            user= null;
        return user !=null;
    }
}
