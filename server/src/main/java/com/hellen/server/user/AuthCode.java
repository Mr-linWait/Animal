package com.hellen.server.user;

import com.hellen.enum_.ResultCodeEnum;
import com.hellen.result.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@RestController
@CrossOrigin
@RequestMapping("code")
public class AuthCode {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String eamilAccount;

    @Resource
    private UserService userService;

    @GetMapping("getCode/{email}")
    public Result getCode (@PathVariable String email){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件title
        simpleMailMessage.setSubject("欢迎新用户注册流浪动物收养系统账号");
        simpleMailMessage.setFrom(eamilAccount);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSentDate(new Date());
        String code = randomSix();
        simpleMailMessage.setText("这是您的验证码： "+code+" 。用于注册账号验证，3分钟内有效，请勿泄露和转发。如非本人操作，请忽略此邮件。");
        userService.insertCode(email,code);
        javaMailSender.send(simpleMailMessage);
        return Result.success();
    }

    @GetMapping("checkCode/{email}/{code}")
    public Result checkCode(@PathVariable String email ,@PathVariable String code){
        if (StringUtils.hasText(email) && StringUtils.hasText(code)){
            if(userService.checkCode(email,code))
                return Result.success();
            else
                return Result.fail("验证码不正确");
        }else
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
    }


        public String randomSix() {
            String digitsAndLetters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(6);
            for (int i = 0; i < 6; i++) {
                sb.append(digitsAndLetters.charAt(random.nextInt(digitsAndLetters.length())));
            }
            return sb.toString();
        }

}
