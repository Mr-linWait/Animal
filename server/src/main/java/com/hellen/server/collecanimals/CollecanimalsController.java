package com.hellen.server.collecanimals;

import com.hellen.base.util.UserUtil;
import com.hellen.entity.client.CollectAnimals;
import com.hellen.entity.manangement.User;
import com.hellen.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("collecanimals")
public class CollecanimalsController {


    @Resource
    private CollecanimalsService collecanimalsService;

    @PostMapping("add")
    public Result add(@RequestBody CollectAnimals collectAnimals) {
        if (collecanimalsService.save(collectAnimals))
            return Result.success(collectAnimals);
        return Result.fail("收藏失败");
    }

    @GetMapping("getState/{animalsId}")
    public Result getState(@PathVariable Long animalsId) {
        User user = UserUtil.getUser();
        if (Objects.isNull(user)) {
            return Result.fail("请登录！");
        }
        CollectAnimals collectAnimals = collecanimalsService.getByOtherId(user.getId(), animalsId);
        if (Objects.isNull(collectAnimals)) {
            return Result.success(false);
        }
        return Result.success(true);
    }

    @PostMapping("cancel")
    public Result cancel(@RequestBody CollectAnimals collectAnimals) {
        if (collecanimalsService.cancel(collectAnimals))
            return Result.success();
        return Result.fail("删除失败");
    }

    @GetMapping("getCollectionInMessageList")
    public Result getCollectionInMessageList() {
        User user = UserUtil.getUser();
        if (Objects.isNull(user)) {
            return Result.fail("请登录！");
        }
        List<CollectAnimals> list = collecanimalsService.getCollectionInMessageList(user.getId());
        return Result.success(list);
    }
}
