package com.hellen.base.util;

import com.hellen.entity.manangement.User;
import com.hellen.server.user.UserController;

public class UserUtil {

    /**
     * 获取当前登录用户
     * @return
     */
    public static User getUser(){
        User user = UserController.USER_INFO.get();
        return user;
    }


}
