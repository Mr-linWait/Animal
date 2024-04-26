package com.hellen.server.comment;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hellen.entity.client.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {
    int addComment(String comment, Long bizId);

    List<Comment> getCommentByAnimalId(Long animalId);

    List<Comment> getCommentByUserId(Long userId);

    IPage<Comment> getPage(Page<Comment> page);
}
