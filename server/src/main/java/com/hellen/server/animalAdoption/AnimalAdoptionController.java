package com.hellen.server.animalAdoption;

import com.hellen.base.util.UserUtil;
import com.hellen.entity.client.AnimalAdoption;
import com.hellen.entity.manangement.User;
import com.hellen.enum_.ApplyState;
import com.hellen.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("animalAdoption")
public class AnimalAdoptionController {


    @Resource
    private AnimalAdoptionService animalAdoptionService;

    @PostMapping("add")
    public Result add(@RequestBody AnimalAdoption animalAdoption) {
        User user = UserUtil.getUser();
        animalAdoption.setApplyId(user.getId());
        animalAdoption.setApplyState(ApplyState.Applying);
        if (animalAdoptionService.save(animalAdoption)) {
            return Result.success(animalAdoption);
        }
        return Result.fail("收养请求失败");
    }

    @GetMapping("getState/{animalId}")
    public Result getState(@PathVariable("animalId") Long animalId) {
        User user = UserUtil.getUser();
        AnimalAdoption animalAdoption = animalAdoptionService.getByOtherId(user.getId(), animalId);
        if (Objects.isNull(animalAdoption)) {
            return Result.success(false);
        }
        return Result.success(animalAdoption.getApplyState());
    }

    @PostMapping("adoptAgain/{animalId}")
    public Result adoptAgain(@PathVariable("animalId") Long animalId) {
        animalAdoptionService.adoptAgain(animalId);
        return Result.success();
    }

    @GetMapping("getAdoptApplyList")
    public Result getAdoptApplyList() {
        List<AnimalAdoption> list = animalAdoptionService.getAdoptApplyList();
        return Result.success(list);
    }

    @GetMapping("agreeAdopt/{applyId}/{animalId}")
    public Result agreeAdopt(@PathVariable("applyId") Long applyId, @PathVariable("animalId") Long animalId) {
        animalAdoptionService.agreeAdopt(applyId, animalId);
        return Result.success();
    }

    @GetMapping("reject/{applyId}/{animalId}")
    public Result reject(@PathVariable("applyId") Long applyId, @PathVariable("animalId") Long animalId) {
        animalAdoptionService.reject(applyId, animalId);
        return Result.success();
    }

}
