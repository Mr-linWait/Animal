package com.hellen.entity.client;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hellen.enum_.MessageState;
import com.hellen.enum_.MessageType;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_message")
@Data
@Accessors(chain = true)
public class Message {

    @TableField
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long addresserId;//发信人id

    @TableField
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long recipientsId;//收信人 ID

    @TableField
    private MessageState messageState;//消息状态

    @TableField
    private String content;//消息内容

    @TableField
    private MessageType messageType;//消息类型
}
