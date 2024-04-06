package com.hellen.entity.client;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hellen.enum_.ApplyState;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("tb_animalAdoption")
public class AnimalRecord {

    @TableField
    private ApplyState applyState;//申请状态
    @TableField
    private Long animalId;//宠物id

    @TableField
    private Long applyId;//申请人id
}
