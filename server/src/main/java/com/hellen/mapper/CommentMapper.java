package com.hellen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hellen.entity.client.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
