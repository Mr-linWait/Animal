package com.hellen.server.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hellen.entity.manangement.User;
import com.hellen.result.Result;

public interface UserService extends IService<User> {
    Result registUser(User userInfo);

    User login(String account,String password);

    boolean changePassword(User userInfo);

    boolean modifyUserInfoById(User userInfo);

    IPage<User> selectPageUserInfo(Page<User> userPage, User userParam);

    User Adminlogin(String account,String password);

    int deleteUser(Long userId);

}
