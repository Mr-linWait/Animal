package com.hellen.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
@Data
@Accessors(chain = true)
public class BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    @JsonProperty("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifyTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifier;


}
