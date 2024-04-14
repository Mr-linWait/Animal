package com.hellen.entity.client;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hellen.entity.BaseEntity;
import com.hellen.enum_.TrueOrFalse;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_animalHealthInfo")
@Data
@Accessors(chain = true)
public class AnimalHealthInfo extends BaseEntity {

    @TableField
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long animalId;

    @TableField
    private TrueOrFalse sterilization;//绝育

    @TableField
    private TrueOrFalse desinsectization;//驱虫

    @TableField
    private TrueOrFalse immune;//免疫


}
