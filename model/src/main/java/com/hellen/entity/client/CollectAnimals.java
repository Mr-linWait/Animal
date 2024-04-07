package com.hellen.entity.client;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hellen.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户收藏的宠物
 */
@TableName("tb_collecanimals")
@Data
@Accessors(chain = true)
public class CollectAnimals extends BaseEntity {

    @TableField
    private Long animalId;

    @TableField
    private Long userId;

}
