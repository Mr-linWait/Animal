package com.hellen.server.collecanimals;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hellen.base.util.UserUtil;
import com.hellen.entity.BaseEntity;
import com.hellen.entity.client.Animal;
import com.hellen.entity.client.AnimalHealthInfo;
import com.hellen.entity.client.AnimalImg;
import com.hellen.entity.client.CollectAnimals;
import com.hellen.entity.manangement.User;
import com.hellen.mapper.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollecanimalsServiceImpl extends ServiceImpl<CollecanimalsMapper, CollectAnimals> implements CollecanimalsService {

    private final CollecanimalsMapper collecanimalsMapper;
    private final AnimalInfoMapper animalInfoMapper;
    private final UserMapper userMapper;
    private final AnimalHealthInfoMapper animalHealthInfoMapper;
    private final AnimalImgMapper animalImgMapper;

    public CollecanimalsServiceImpl(@Qualifier("collecanimalsMapper") CollecanimalsMapper collecanimalsMapper, AnimalInfoMapper animalInfoMapper, UserMapper userMapper, AnimalHealthInfoMapper animalHealthInfoMapper, AnimalImgMapper animalImgMapper) {
        this.collecanimalsMapper = collecanimalsMapper;
        this.animalInfoMapper = animalInfoMapper;
        this.userMapper = userMapper;
        this.animalHealthInfoMapper = animalHealthInfoMapper;
        this.animalImgMapper = animalImgMapper;
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

    public List<CollectAnimals> getCollectionInMessageList(Long userId) {
        //user_petList
        Animal animalParam = new Animal();
        animalParam.setAge(null);
        animalParam.setReward(null);
        animalParam.setModifier(String.valueOf(userId));
        QueryWrapper<Animal> animalQueryWrapper = new QueryWrapper<>();
        animalQueryWrapper.setEntity(animalParam);
        List<Animal> animals = animalInfoMapper.selectList(animalQueryWrapper);
        if (animals.isEmpty()) {
            return new ArrayList<>();
        }

        List<Long> animalIds = animals.stream().map(BaseEntity::getId).collect(Collectors.toList());

        QueryWrapper<CollectAnimals> wrapper = new QueryWrapper<>();
        wrapper.in("animalId", animalIds);
        wrapper.ne("userId", userId);
        List<CollectAnimals> collectAnimals = collecanimalsMapper.selectList(wrapper);
        for (CollectAnimals collectAnimal : collectAnimals) {
            if (Objects.nonNull(collectAnimal.getUserId())) {
                User user = userMapper.selectById(collectAnimal.getUserId());
                collectAnimal.setUserName(user.getUserName());
            }
            if (Objects.nonNull(collectAnimal.getAnimalId())) {
                Animal animal = animalInfoMapper.selectById(collectAnimal.getAnimalId());
                collectAnimal.setAnimalName(animal.getName());
            }
        }

        return collectAnimals;
    }

    @Override
    public IPage<Animal> getCollectionList(String animalState, Page<Animal> animalPage) {
        User user = UserUtil.getUser();
        IPage<Animal> animalList = collecanimalsMapper.getCollectionList(animalPage, animalState, user.getId());
        for (Animal record : animalList.getRecords()) {
            AnimalHealthInfo animalHealthInfo = animalHealthInfoMapper.selectByAnimalId(record.getId());
            record.setAnimalHealthInfo(animalHealthInfo);

            List<AnimalImg> animalImgList = animalImgMapper.selectListByAnimalId(record.getId());
            record.setAnimalImgList(animalImgList);
        }
        return animalList;
    }
}
