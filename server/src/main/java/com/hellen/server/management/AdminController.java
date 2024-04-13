package com.hellen.server.management;

import com.hellen.entity.manangement.User;
import com.hellen.enum_.ResultCodeEnum;
import com.hellen.result.Result;
import com.hellen.server.user.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员登录
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    private UserService userService;

    @GetMapping("/adminLogin")
    public Result adminLogin(@RequestParam() User user, HttpServletRequest request){
        if (StringUtils.hasText(user.getAccount()) || StringUtils.hasText(user.getPassword()))
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        User adminlogin = userService.Adminlogin(user.getAccount(), user.getPassword());
        if (user!=null){
            HttpSession session = request.getSession(true);
            session.setAttribute("loginUser",adminlogin);
            return Result.success("登录成功！");
        }else {
            return Result.fail(ResultCodeEnum.NOT_REGIST);
        }
    }

    @DeleteMapping("deleteUser/{userId}")
    public Result deleteUser(@PathVariable Long userId){
        if(userService.deleteUser(userId)>0)
            return Result.success("删除用户成功！");
        else
            return Result.fail("删除用户失败了！");
    }
}
