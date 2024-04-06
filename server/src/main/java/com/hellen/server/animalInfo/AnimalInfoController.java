package com.hellen.server.animalInfo;

import com.hellen.entity.client.Animal;
import com.hellen.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("animalInfo")
public class AnimalInfoController {

    @Resource
    private AnimalInfoService animalInfoService;
    @GetMapping("hotAnimal")
    public Result hotAnimal(){
        List<Animal> animalList=animalInfoService.hotAnimalInfoList();
        return Result.success(animalList);
    }
}
