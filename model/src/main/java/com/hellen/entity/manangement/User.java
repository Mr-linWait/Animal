package com.hellen.entity.manangement;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hellen.entity.BaseEntity;
import com.hellen.enum_.UserType;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("tb_user")
public class User extends BaseEntity {

    @TableField
    private String account;//用户名
    @TableField
    private String userName;//名字
    @TableField
    private String password;
    @TableField
    private String tel;
    @TableField
    private String address;
    @TableField
    private String email;

    @TableField
    private String signature;
    @TableField
    private UserType userType;
}
