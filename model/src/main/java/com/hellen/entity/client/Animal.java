package com.hellen.entity.client;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hellen.entity.BaseEntity;
import com.hellen.enum_.AnimalState;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

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
    private Integer age;

    @TableField
    private String gender;

    @TableField
    private AnimalState animalState;//动物状态
    @TableField
    private String province;//省份

    @TableField
    private String city;

    @TableField
    private Integer state;//宠物状态 0为未审核 1 审核通过 -1 驳回

    @TableField
    private String description;//描述

    @TableField
    private Integer reward;//寻宠报酬


    /**/
    @TableField(exist = false)
    private AnimalHealthInfo animalHealthInfo;

    @TableField(exist = false)
    private List<AnimalImg> animalImgList;
}
