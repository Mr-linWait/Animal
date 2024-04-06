package com.hellen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hellen.entity.client.Animal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnimalInfoMapper extends BaseMapper<Animal> {

    List<Animal> hotAnimalInfo();
}
