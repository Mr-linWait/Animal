package com.hellen.entity.client;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hellen.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_animalImg")
@Data
@Accessors(chain = true)
public class AnimalImg extends BaseEntity {
    @TableField
    private String path;

    @TableField
    private Long animalId;



}
