package com.hellen.entity.client;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hellen.entity.BaseEntity;
import com.hellen.enum_.AnimalState;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_animal")
@Data
@Accessors(chain = true)
public class Animal extends BaseEntity {
    @TableField
    private String name;//宠物名字
    @TableField
    private String type;//类型

    @TableField()
    private String species;//品种

    @TableField
    private int age;

    @TableField
    private String gender;

    @TableField
    private AnimalState animalState;//动物状态
    @TableField
    private String province;//省份

    @TableField
    private String city;

    @TableField
    private Integer needCardNum;//需要打卡次数

    @TableField
    private String description;//描述

}
