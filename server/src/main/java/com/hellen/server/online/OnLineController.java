package com.hellen.server.online;

import com.hellen.entity.client.Message;
import com.hellen.result.Result;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("online")
@CrossOrigin
public class OnLineController {

    @Resource
    private OnLineService onLineService;

    @GetMapping("getUserMessage/{addresserId}/{recipientsId}")
    public Result getUserMessage(@PathVariable Long addresserId, @PathVariable Long recipientsId) {
        List<Message> messageList = onLineService.getUserMessage(addresserId, recipientsId);
        return Result.success().setData(messageList);
    }

    @PostMapping("sendMessage")
    public Result sendMessage(@RequestBody Message message){
        if (!StringUtils.hasText(message.getContent()))
            return Result.fail("不允许发送空白消息！");
        if(onLineService.addMessage(message)){
            return Result.success();
        }else
            return Result.fail("发送失败");
    }

    @GetMapping("getAllActiveOnLine")
    public Result getAllActiveOnLine(){
        List<Message> allActiveOnLine = onLineService.getAllActiveOnLine();
        return Result.success(allActiveOnLine);
    }
}
