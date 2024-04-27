package com.hellen.server.animalInfo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hellen.entity.client.Animal;

import java.util.List;

public interface AnimalInfoService extends IService<Animal> {
    List<Animal> hotAnimalInfoList();

    IPage<Animal> selectPageAnimalInfo(Page<Animal> animalPage, Animal animalParam);

    boolean saveAnimalInfo(Animal animalInfo);

    List<Animal> hotSearchAnimal();


    Animal getInfoById(Long id);

    IPage<Animal> getSendAnimalInfoList(Page<Animal> animalPage, Animal animalParam);

    IPage<Animal> getSearchAnimalInfoList(Page<Animal> animalPage, Animal animalParam);

    boolean approvalAnimal(Long animalId);

    boolean rejectAnimal(Long animalId);

    List<Animal> animalList(Page<Animal> animalPage);

    IPage<Animal> getMySendAnimalPage(Page<Animal> animalPage);

    IPage<Animal> getMySearchAnimalPage(Page<Animal> animalPage);
}
