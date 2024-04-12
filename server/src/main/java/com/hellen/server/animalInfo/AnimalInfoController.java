package com.hellen.server.animalInfo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hellen.entity.client.Animal;
import com.hellen.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("animalInfoList/{current}/{pageSize}")
    public Result animalInfoList(@RequestParam(required = false) Animal animalParam, @PathVariable Long current,@PathVariable Long pageSize){
        Page<Animal> animalPage = new Page<>(current, pageSize);
        IPage<Animal> animalIPage=animalInfoService.selectPageAnimalInfo(animalPage,animalParam);
        return Result.success(animalIPage);
    }
}
