package com.hellen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hellen.entity.client.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OnLineMapper extends BaseMapper<Message> {
    List<Message> getNewMessage(@Param("addresserId") Long addresserId,@Param("recipientsId") Long recipientsId);

    List<Message> getHisMessage(@Param("addresserId")Long addresserId,@Param("recipientsId") Long recipientsId);

    int readAllMessage(@Param("addresserId")Long addresserId,@Param("recipientsId")Long recipientsId);

    List<Message> getAllActiveOnLine(@Param("addresserId")Long userId);
}
