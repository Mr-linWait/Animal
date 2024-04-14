package com.hellen.server.animalInfo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hellen.base.util.UserUtil;
import com.hellen.entity.client.Animal;
import com.hellen.entity.manangement.User;
import com.hellen.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("animalInfo")
@CrossOrigin
public class AnimalInfoController {

    @Resource
    private AnimalInfoService animalInfoService;
    @GetMapping("hotAnimal")
    public Result hotAnimal(){
        List<Animal> animalList=animalInfoService.hotAnimalInfoList();
        return Result.success(animalList);
    }

    /**
     * 热门的寻宠信息
     * @return
     */
    @GetMapping("hotSearchAnimal")
    public Result hotSearchAnimal(){
        List<Animal> animalList=animalInfoService.hotSearchAnimal();
        return Result.success(animalList);
    }

    @GetMapping("animalInfoList/{current}/{pageSize}")
    public Result animalInfoList(@RequestParam(required = false) Animal animalParam, @PathVariable Long current,@PathVariable Long pageSize){
        Page<Animal> animalPage = new Page<>(current, pageSize);
        IPage<Animal> animalIPage=animalInfoService.selectPageAnimalInfo(animalPage,animalParam);
        return Result.success(animalIPage);
    }

    @PostMapping("/add")
    public Result add(@RequestBody() Animal animalInfo){
        if(animalInfoService.saveAnimalInfo(animalInfo)){
            return Result.success(animalInfo);
        }
        return Result.fail("保存失败了！");
    }

    @GetMapping("/get/{id}")
    public Result getById( @PathVariable Long id) {
        Animal animal = animalInfoService.getInfoById(id);
        if (Objects.isNull(animal)) {
            return Result.fail("请选择正确的！");
        }
        return Result.success(animal);
    }
}
