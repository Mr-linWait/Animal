package com.hellen.server.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hellen.base.util.SecurityUtil;
import com.hellen.base.util.UserUtil;
import com.hellen.entity.manangement.User;
import com.hellen.enum_.ResultCodeEnum;
import com.hellen.result.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    /**
     * 存放登录的用户信息
     */
    public static final ThreadLocal<User> USER_INFO = new ThreadLocal<>();

    @Resource
    private UserService userService;

    @GetMapping("login/{account}/{password}")
    public Result login(HttpServletRequest request, @PathVariable(required = true) String account, @PathVariable String password) {
        User loginUser = userService.login(account, password);
        if (loginUser != null) {
            USER_INFO.set(loginUser);
            UserUtil.setActiveUser(loginUser.getId(), loginUser);
        } else
            return Result.fail("账号或者密码不正确！");
        return Result.success(loginUser);
    }

    @DeleteMapping("logOut/{userId}")
    public Result logOut(@PathVariable Long userId, HttpServletRequest request) {
        UserUtil.reomveUser();
        return Result.success();
    }

    @PostMapping("regist")
    public Result regist(@RequestBody() User userInfo) {
        if (userInfo == null)
            return Result.fail("注册失败！");
        Result result = userService.registUser(userInfo);
        return result;
    }

    @PostMapping("changePsd")
    public Result changePsd(@RequestParam() User userInfo) {
        if (!StringUtils.hasText(userInfo.getAccount()) || !StringUtils.hasText(userInfo.getPassword()))
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        if (userService.changePassword(userInfo))
            return Result.success();
        else
            return Result.fail("修改密码失败！");
    }

    @PostMapping("modifyUserInfo")
    public Result modifyUserInfo(@RequestBody User userInfo) {
        if (userInfo == null)
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        if (userService.modifyUserInfoById(userInfo))
            return Result.success();

        return Result.fail("保存用户失败！请重新尝试");
    }

    @GetMapping("getUserList/{current}/{pageSize}")
    public Result getUserList(@RequestParam(required = false) User user, @PathVariable long current, @PathVariable long pageSize) {
        Page<User> userPage = new Page<>(current, pageSize);
        IPage<User> userIPage = userService.selectPageUserInfo(userPage, user);
        return Result.success(userIPage);
    }

    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id) {
        User user = userService.getById(id);
        user.setPassword(null);
        return Result.success(user);
    }


    @GetMapping("changePwd/{oldPwd}/{newPwd}")
    public Result changePwd(@PathVariable String oldPwd, @PathVariable String newPwd) {
        User user = UserUtil.getUser();
        if (!user.getPassword().equals(SecurityUtil.encryptPasswordWithSaltAndSHA256(oldPwd))) {
            return Result.fail("旧密码错误！");
        }
        userService.updatePwd(user.getId(), SecurityUtil.encryptPasswordWithSaltAndSHA256(newPwd));
        return Result.success();
    }

}
