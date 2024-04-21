package com.hellen.server.online;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hellen.base.util.UserUtil;
import com.hellen.entity.client.Message;
import com.hellen.entity.manangement.User;
import com.hellen.enum_.MessageState;
import com.hellen.enum_.MessageType;
import com.hellen.mapper.OnLineMapper;
import com.hellen.server.user.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
public class OnLineServiceImpl extends ServiceImpl<OnLineMapper, Message> implements OnLineService {

    @Resource
    private OnLineMapper onLineMapper;

    @Resource
    private UserService userService;


    /**
     * 获取最新消息 都是未读
     * @param addresserId
     * @param recipientsId
     * @return
     */
    @Override
    public List<Message> getUserMessage(Long addresserId, Long recipientsId) {
        List<Message> newMessage = onLineMapper.getNewMessage(addresserId, recipientsId);
        return newMessage;
    }

    /**
     * 新增消息
     * @param message
     * @return
     */
    @Override
    public boolean addMessage(Message message) {
        message.setMessageState(MessageState.Delivery);
        message.setMessageType(MessageType.TEXT);
        int insert = onLineMapper.insert(message);
        return insert > 0;
    }

    /**
     * 获取消息记录
     * @param addresserId
     * @param recipientsId
     * @return
     */
    @Override
    @Deprecated
    public List<Message> getMessageRecord(Long addresserId, Long recipientsId){
        //List<Message> newMessage = getNewMessage(addresserId, recipientsId);
        List<Message> hisMessage=getHisMessage(addresserId,recipientsId);
        //hisMessage.addAll(newMessage);
        return hisMessage;
    }

    @Override
    public List<Message> getHisMessage(Long addresserId, Long recipientsId) {
        List<Message> hisMessage=onLineMapper.getHisMessage(addresserId, recipientsId);
        return hisMessage;
    }

    @Override
    @Deprecated
    public void readAllMessage(Long addresserId, Long recipientsId){
        onLineMapper.readAllMessage(addresserId,recipientsId);
    }


    @Override
    public List<Message> getAllActiveOnLine(){
        User user = UserUtil.getUser();
        Long userId = user.getId();
        List<Message> messageList=onLineMapper.getAllActiveOnLine(userId);
        if (!CollectionUtils.isEmpty(messageList)) {
            Message message = messageList.get(0);
            message.setCureentMessageList(getHisMessage(message.getAddresserId(),message.getRecipientsId()));
            for (Message msg : messageList) {
                if (userId.equals(msg.getRecipientsId())){
                    User byId = userService.getById(msg.getAddresserId());
                    msg.setRecipientsName(byId.getUserName());
                }else {
                    User byId = userService.getById(msg.getRecipientsId());
                    msg.setRecipientsName(byId.getUserName());
                }
            }
        }
        return messageList;
    }
}
