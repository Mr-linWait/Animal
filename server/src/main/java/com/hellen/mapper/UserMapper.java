package com.hellen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.hellen.entity.manangement.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    int checkCode(@Param("email") String email,@Param("code")  String code,@Param("localDateTime")  LocalDateTime localDateTime);

    void insertCode(@Param("email") String email,@Param("code")  String code,@Param("localDateTime")  LocalDateTime localDateTime);

}
