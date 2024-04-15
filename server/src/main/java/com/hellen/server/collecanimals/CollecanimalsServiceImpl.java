package com.hellen.server.collecanimals;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hellen.entity.client.Animal;
import com.hellen.entity.client.CollectAnimals;
import com.hellen.mapper.AnimalInfoMapper;
import com.hellen.mapper.CollecanimalsMapper;
import com.hellen.server.animalInfo.AnimalInfoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CollecanimalsServiceImpl extends ServiceImpl<CollecanimalsMapper, CollectAnimals> implements CollecanimalsService {

    private final CollecanimalsMapper collecanimalsMapper;

    public CollecanimalsServiceImpl(@Qualifier("collecanimalsMapper") CollecanimalsMapper collecanimalsMapper) {
        this.collecanimalsMapper = collecanimalsMapper;
    }

    public CollectAnimals getByOtherId(Long userId, Long animalsId) {
        CollectAnimals collectAnimals = new CollectAnimals();
        collectAnimals.setUserId(userId);
        collectAnimals.setAnimalId(animalsId);

        QueryWrapper<CollectAnimals> wrapper = new QueryWrapper<>();
        wrapper.setEntity(collectAnimals);

        CollectAnimals result = collecanimalsMapper.selectOne(wrapper);
        return result;
    }

    public boolean cancel(CollectAnimals collectAnimals) {
        QueryWrapper<CollectAnimals> wrapper = new QueryWrapper<>(collectAnimals);
        return collecanimalsMapper.delete(wrapper) == 1;
    }
}
