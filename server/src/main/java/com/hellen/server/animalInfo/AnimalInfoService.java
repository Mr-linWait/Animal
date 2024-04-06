package com.hellen.server.animalInfo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hellen.entity.client.Animal;

import java.util.List;

public interface AnimalInfoService extends IService<Animal> {
    List<Animal> hotAnimalInfoList();

}
