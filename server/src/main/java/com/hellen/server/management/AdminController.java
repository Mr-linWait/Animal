package com.hellen.server.management;

import com.hellen.entity.client.Animal;
import com.hellen.entity.manangement.User;
import com.hellen.enum_.ResultCodeEnum;
import com.hellen.result.Result;
import com.hellen.server.animalInfo.AnimalInfoService;
import com.hellen.server.user.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员登录
 */
@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {

    @Resource
    private UserService userService;

    @Resource
    private AnimalInfoService animalInfoService;

    @GetMapping("/adminLogin/{account}/{password}")
    public Result adminLogin(@PathVariable String account,@PathVariable String password, HttpServletRequest request){
        if ( !StringUtils.hasText(account) || !StringUtils.hasText(password))
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        User adminlogin = userService.Adminlogin(account,password);
        if (adminlogin!=null){
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

    @PutMapping("approvalAnimal/{animalId}")
    public Result approvalAnimal(@PathVariable Long animalId){
        if (animalInfoService.approvalAnimal(animalId))
            return Result.success();
        else
            return Result.fail("审批失败！");
    }

    @PutMapping("rejectAnimal/{animalId}")
    public Result rejectAnimal(@PathVariable Long animalId){
        if (animalInfoService.rejectAnimal(animalId))
            return Result.success();
        else
            return Result.fail("驳回失败！");
    }

    @PutMapping("animalList")
    public Result animalList(){
        List<Animal> animalList=animalInfoService.animalList();
        return Result.success(animalList);
    }
}
