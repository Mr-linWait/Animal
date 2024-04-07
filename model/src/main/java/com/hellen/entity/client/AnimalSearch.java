package com.hellen.entity.client;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hellen.enum_.ApplyState;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 寻宠
 */
@TableName("tb_animalSearch")
@Data
@Accessors(chain = true)
public class AnimalSearch {

    @TableField
    private ApplyState applyState;//申请状态
    @TableField
    private Long animalId;//宠物id

    @TableField
    private Long applyId;//申请人id

}
