package com.hellen.server.online;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hellen.entity.client.Message;

import java.util.List;

public interface OnLineService extends IService<Message> {
    List<Message> getUserMessage(Long addresserId, Long recipientsId);

    boolean addMessage(Message message);

    List<Message> getMessageRecord(Long addresserId, Long recipientsId);

    List<Message> getHisMessage(Long addresserId, Long recipientsId);

    void readAllMessage(Long addresserId, Long recipientsId);

    List<Message> getAllActiveOnLine();
}
