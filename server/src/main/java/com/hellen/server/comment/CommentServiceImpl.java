package com.hellen.server.comment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hellen.base.util.UserUtil;
import com.hellen.entity.client.Comment;
import com.hellen.entity.manangement.User;
import com.hellen.enum_.UserType;
import com.hellen.mapper.CommentMapper;
import com.hellen.server.user.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserService userService;

    @Override
    public int addComment(String comment, Long bizId) {
        User user = UserUtil.getUser();
        Long userId = null;
        if (user != null)
            userId = user.getId();
        Comment commentEntity = new Comment().setComment(comment).setBizId(bizId)
                .setBizType(UserType.User).setUserId(userId);
        commentEntity.setCreateTime(LocalDateTime.now());
        int insert = commentMapper.insert(commentEntity);
        return insert;
    }

    @Override
    public List<Comment> getCommentByAnimalId(Long animalId) {
        Comment comment = new Comment().setBizId(animalId);
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.orderByDesc("createTime");
        commentQueryWrapper.setEntity(comment);
        List<Comment> comments = commentMapper.selectList(commentQueryWrapper);
        for (Comment c : comments) {
            if (c.getUserId()!=null){
                User byId = userService.getById(c.getUserId());
                c.setUsername(byId.getUserName());
            }
        }
        return comments;
    }
}
