package com.hellen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hellen.entity.client.Animal;
import com.hellen.entity.client.AnimalHealthInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnimalInfoMapper extends BaseMapper<Animal> {

    List<Animal> hotAnimalInfo();

    int insertHealthInfo(AnimalHealthInfo animalHealthInfo);

    List<Animal> hotSearchAnimal();

    List<Animal> animalList();

    IPage<Animal> getMySendAnimalPage(Page<Animal> animalPage, Long userId);

    IPage<Animal> getMySearchAnimalPage(Page<Animal> animalPage, Long userId);
}
