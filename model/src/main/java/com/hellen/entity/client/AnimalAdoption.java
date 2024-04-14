package com.hellen.entity.client;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hellen.enum_.ApplyState;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户收养的宠物
 */
@Data
@Accessors(chain = true)
@TableName("tb_animalAdoption")
public class AnimalAdoption {

    @TableField
    private ApplyState applyState;//申请状态
    @TableField
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long animalId;//宠物id

    @TableField
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long applyId;//申请人id
}
