package com.hellen.server.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hellen.base.util.SecurityUtil;
import com.hellen.entity.manangement.User;
import com.hellen.enum_.UserType;
import com.hellen.mapper.UserMapper;
import com.hellen.result.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Result registUser(User userInfo) {
        userInfoCheck(userInfo);
        userInfo.setUserType(UserType.User);
        userInfo.setPassword(SecurityUtil.encryptPasswordWithSaltAndSHA256(userInfo.getPassword()));
        if (this.save(userInfo))
            return Result.success();
        return Result.fail("注册失败！");
    }

    /**
     * 登录
     *
     * @param account  账户名
     * @param password 密码
     * @return
     */

    @Override
    public User login(String account, String password) {
        User user = new User();
        user.setAccount(account);
        user.setUserType(UserType.User);
        user.setPassword(SecurityUtil.encryptPasswordWithSaltAndSHA256(password));
        LambdaQueryChainWrapper<User> userLambdaQueryChainWrapper = this.lambdaQuery(user);
        user = userLambdaQueryChainWrapper.one();

        return user;
    }

    /**
     * 修改密码
     *
     * @param userInfo 用户信息 包含用户名和密码
     * @return
     */
    @Override
    public boolean changePassword(User userInfo) {
        UpdateChainWrapper<User> set = update().eq("id", userInfo.getId()).set("password", userInfo.getPassword());
        int update = userMapper.update(set);
        return update > 0;
    }

    /**
     * 修改用户信息！不包括密码
     *
     * @param userInfo
     * @return
     */
    @Override
    public boolean modifyUserInfoById(User userInfo) {
        userInfo.setPassword(null);
        return updateById(userInfo);
    }

    @Override
    public IPage<User> selectPageUserInfo(Page<User> userPage, User userParam) {
        QueryChainWrapper<User> userQueryChainWrapper = new QueryChainWrapper<User>(userMapper);
        if (StringUtils.hasText(userParam.getUserName()))
            userQueryChainWrapper.like("username", "%"+userParam.getUserName()+"%");
        if (StringUtils.hasText(userParam.getAccount()))
            userQueryChainWrapper.eq("account",userParam.getAccount());
        if (userParam.getCreateTime()!=null)
            userQueryChainWrapper.gt("createTime",userParam.getCreateTime());
        Page<User> iPage = userMapper.selectPage(userPage, userQueryChainWrapper);
        return iPage;
    }

    @Override
    public User Adminlogin(String account,String password) {
        User user = new User();
        user.setAccount(account);
        user.setPassword(SecurityUtil.encryptPasswordWithSaltAndSHA256(password));
        user.setUserType(UserType.Admin);
        LambdaQueryChainWrapper<User> userLambdaQueryChainWrapper = this.lambdaQuery(user);
        user = userLambdaQueryChainWrapper.one();
        return user;
    }

    @Override
    public int deleteUser(Long userId) {
        return userMapper.deleteById(userId);
    }

    private static final String LANDLINE_PHONE_REGEX = "^\\(?\\d{3,4}\\)?[- .]?\\d{7,8}$";//固定电话校验
    private static final String MOBILE_PHONE_REGEX = "^1[3-9]\\d{9}$";//手机校验

    /**
     * 检查用户信息是否合法
     *
     * @param userInfo
     * @return
     */
    private Result userInfoCheck(User userInfo) {
        if (StringUtils.hasText(userInfo.getPassword()) || StringUtils.hasText(userInfo.getAccount()))
            return Result.fail("用户名密码不能为空！");
        if (userInfo.getPassword().length() < 8)
            return Result.fail("密码不能小于8位！");
        if (userInfo.getAccount().length() > 8)
            return Result.fail("用户名不能大于8位");
        String tel = userInfo.getTel();
        if (StringUtils.hasText(tel)) {
            Pattern pattern = Pattern.compile(MOBILE_PHONE_REGEX);
            Matcher matcherPhone = pattern.matcher(tel);
            Pattern compile = Pattern.compile(LANDLINE_PHONE_REGEX);
            Matcher matcher = compile.matcher(tel);
            if (!(matcher.matches() || matcherPhone.matches()))
                return Result.fail("手机号码不符合规范");
        }
        return Result.success();
    }

}
