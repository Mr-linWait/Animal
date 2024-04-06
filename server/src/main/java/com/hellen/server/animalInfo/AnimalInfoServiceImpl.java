package com.hellen.server.animalInfo;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hellen.entity.client.Animal;
import com.hellen.mapper.AnimalInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalInfoServiceImpl extends ServiceImpl<AnimalInfoMapper, Animal> implements AnimalInfoService {

    @Resource
    private AnimalInfoMapper animalInfoMapper;

    @Override
    public List<Animal> hotAnimalInfoList() {
        List<Animal> animals = animalInfoMapper.hotAnimalInfo();
        return animals;
    }
}
