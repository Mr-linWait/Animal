package com.hellen.server.comment;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hellen.base.util.UserUtil;
import com.hellen.entity.client.Comment;
import com.hellen.entity.manangement.User;
import com.hellen.result.Result;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @PutMapping("addComment/{comment}/{bizId}")
    public Result addComment(@PathVariable String comment, @PathVariable Long bizId) {
        if (!StringUtils.hasText(comment) || bizId == null)
            return Result.fail("请填写内容！");
        if (commentService.addComment(comment, bizId) > 0) {
            return Result.success();
        } else
            return Result.fail("发布评论失败！");
    }

    @GetMapping("getComment/{animalId}")
    public Result getComment(@PathVariable Long animalId) {
        List<Comment> commentList = commentService.getCommentByAnimalId(animalId);
        return Result.success().setData(commentList);
    }

    @GetMapping("getCommentList")
    public Result getCommentList() {
        User user = UserUtil.getUser();
        if (Objects.isNull(user)) {
            return Result.fail("请登录！");
        }
        List<Comment> commentList = commentService.getCommentByUserId(user.getId());
        return Result.success().setData(commentList);
    }

    @GetMapping("page/{current}/{pageSize}")
    public Result page(@PathVariable Long current, @PathVariable Long pageSize){
        Page<Comment> page = new Page<>(current, pageSize);
        IPage<Comment> result=commentService.getPage(page);
        return Result.success(result);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = commentService.removeById(id);
        return success ? Result.success() : Result.fail("删除失败");
    }
}
