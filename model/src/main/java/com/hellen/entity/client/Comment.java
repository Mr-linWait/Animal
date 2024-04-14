package com.hellen.entity.client;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hellen.entity.BaseEntity;
import com.hellen.enum_.UserType;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_comment")
@Data
@Accessors(chain = true)
public class Comment extends BaseEntity {

    @TableField
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long bizId;//动物id

    @TableField
    private UserType bizType;

    @TableField
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;//用户id

    @TableField
    private String comment;//评论内容

    @TableField(exist = false)
    private String username;//用户的账户名

}
