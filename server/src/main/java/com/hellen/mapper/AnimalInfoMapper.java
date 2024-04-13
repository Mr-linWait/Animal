package com.hellen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hellen.entity.client.Animal;
import com.hellen.entity.client.AnimalHealthInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnimalInfoMapper extends BaseMapper<Animal> {

    List<Animal> hotAnimalInfo();

    int  insertHealthInfo(AnimalHealthInfo animalHealthInfo);

    List<Animal> hotSearchAnimal();
}
