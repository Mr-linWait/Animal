package com.hellen.server.comment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hellen.base.util.UserUtil;
import com.hellen.entity.BaseEntity;
import com.hellen.entity.client.Animal;
import com.hellen.entity.client.AnimalImg;
import com.hellen.entity.client.Comment;
import com.hellen.entity.manangement.User;
import com.hellen.enum_.UserType;
import com.hellen.mapper.AnimalImgMapper;
import com.hellen.mapper.AnimalInfoMapper;
import com.hellen.mapper.CommentMapper;
import com.hellen.mapper.UserMapper;
import com.hellen.server.user.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserService userService;

    @Resource
    private AnimalImgMapper animalImgMapper;
    @Autowired
    private AnimalInfoMapper animalInfoMapper;
    @Autowired
    private UserMapper userMapper;

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
            if (c.getUserId() != null) {
                User byId = userService.getById(c.getUserId());
                c.setUsername(byId.getUserName());
            }
        }
        return comments;
    }

    public List<Comment> getCommentByUserId(Long userId) {
        //user_petList
        Animal animal = new Animal();
        animal.setAge(null);
        animal.setReward(null);
        animal.setModifier(userId.toString());
        QueryWrapper<Animal> animalQueryWrapper = new QueryWrapper<>();
        animalQueryWrapper.setEntity(animal);
        List<Animal> animals = animalInfoMapper.selectList(animalQueryWrapper);
        if (animals.isEmpty()) {
            return new ArrayList<>();
        }

        List<Long> animalIds = animals.stream().map(BaseEntity::getId).collect(Collectors.toList());

        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.orderByDesc("createTime");
        commentQueryWrapper.ne("userId", userId);
        commentQueryWrapper.in("bizId", animalIds);
        List<Comment> comments = commentMapper.selectList(commentQueryWrapper);
        for (Comment c : comments) {
            if (c.getUserId() != null) {
                User byId = userService.getById(c.getUserId());
                c.setUsername(byId.getUserName());
            }
            if (c.getBizId() != null) {
                List<AnimalImg> animalImgList = animalImgMapper.selectListByAnimalId(c.getBizId());
                c.setAnimalImgUrl(animalImgList.isEmpty() ? "" : animalImgList.get(0).getUrl());
            }
        }
        return comments;
    }

    @Override
    public IPage<Comment> getPage(Page<Comment> page) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        Page<Comment> commentPage = commentMapper.selectPage(page, wrapper);
        for (Comment record : commentPage.getRecords()) {
            Animal animal = animalInfoMapper.selectById(record.getBizId());
            record.setBizName(animal.getName());
            User user = userMapper.selectById(record.getUserId());
            if (user!=null)
            record.setUsername(user.getUserName());
        }
        return commentPage;
    }
}
