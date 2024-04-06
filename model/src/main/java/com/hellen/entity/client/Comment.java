package com.hellen.entity.client;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hellen.enum_.UserType;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_comment")
@Data
@Accessors(chain = true)
public class Comment {

    @TableField
    private Long bizId;

    @TableField
    private UserType bizType;

    @TableField
    private String comment;//评论内容

}
