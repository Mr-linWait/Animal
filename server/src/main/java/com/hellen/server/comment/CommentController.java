package com.hellen.server.comment;

import com.hellen.entity.client.Comment;
import com.hellen.result.Result;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @PutMapping("addComment/{comment}/{bizId}")
    public Result addComment(@PathVariable String comment,@PathVariable Long bizId){
        if (!StringUtils.hasText(comment) || bizId==null)
            return Result.fail("请填写内容！");
        if (commentService.addComment(comment,bizId)>0){
            return Result.success();
        }else
            return Result.fail("发布评论失败！");
    }

    @GetMapping("getComment/{animalId}")
    public Result getComment(@PathVariable Long animalId){
        List<Comment> commentList=commentService.getCommentByAnimalId(animalId);
        return Result.success().setData(commentList);
    }
}
