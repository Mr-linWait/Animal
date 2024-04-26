package com.hellen.server.animalInfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hellen.base.util.UserUtil;
import com.hellen.entity.client.Animal;
import com.hellen.entity.client.AnimalHealthInfo;
import com.hellen.entity.client.AnimalImg;
import com.hellen.entity.manangement.User;
import com.hellen.enum_.AnimalState;
import com.hellen.mapper.AnimalHealthInfoMapper;
import com.hellen.mapper.AnimalImgMapper;
import com.hellen.mapper.AnimalInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class AnimalInfoServiceImpl extends ServiceImpl<AnimalInfoMapper, Animal> implements AnimalInfoService {

    @Resource
    private AnimalInfoMapper animalInfoMapper;

    @Resource
    private AnimalImgMapper animalImgMapper;

    @Resource
    private AnimalHealthInfoMapper animalHealthInfoMapper;

    @Override
    public List<Animal> hotAnimalInfoList() {
        List<Animal> animals = animalInfoMapper.hotAnimalInfo();
        return animals;
    }

    @Override
    public List<Animal> hotSearchAnimal() {
        List<Animal> animals = animalInfoMapper.hotSearchAnimal();
        return animals;
    }

    @Override
    public IPage<Animal> selectPageAnimalInfo(Page<Animal> animalPage, Animal animalParam) {
        QueryChainWrapper<Animal> animalQueryChainWrapper = new QueryChainWrapper<Animal>(animalInfoMapper);
        if (StringUtils.hasText(animalParam.getName()))
            animalQueryChainWrapper.like("name", "%" + animalParam.getName() + "%");
        if (animalParam.getAge() > 0)
            animalQueryChainWrapper.gt("age", animalParam.getAge());
        if (StringUtils.hasText(animalParam.getProvince()))
            animalQueryChainWrapper.eq("province", animalParam.getProvince());
        if (StringUtils.hasText(animalParam.getCity()))
            animalQueryChainWrapper.eq("city", animalParam.getCity());
        Page<Animal> animalPageList = animalInfoMapper.selectPage(animalPage, animalQueryChainWrapper);
        return animalPageList;
    }

    @Override
    public boolean saveAnimalInfo(Animal animalInfo) {
        if (animalInfo.getAnimalHealthInfo() == null && animalInfo.getAnimalState() == AnimalState.send)
            return false;
        boolean save = save(animalInfo);
        AnimalHealthInfo animalHealthInfo = animalInfo.getAnimalHealthInfo();
        if (animalHealthInfo != null)
            animalHealthInfoMapper.insert(animalHealthInfo.setAnimalId(animalInfo.getId()));
        if (!CollectionUtils.isEmpty(animalInfo.getAnimalImgList())) {
            User user = UserUtil.getUser();
            String userId = "";
            if (user != null)
                userId = String.valueOf(user.getId());
            for (AnimalImg animalImg : animalInfo.getAnimalImgList()) {
                animalImg.setId(IdWorker.getId());
            }
            animalImgMapper.batchInsert(animalInfo.getAnimalImgList(), animalInfo.getId(), userId);
        }
        return save;
    }

    @Override
    public Animal getInfoById(Long id) {
        Animal animal = animalInfoMapper.selectById(id);
        if (Objects.isNull(animal)) {
            return null;
        }
        AnimalHealthInfo animalHealthInfo = animalHealthInfoMapper.selectByAnimalId(id);
        animal.setAnimalHealthInfo(animalHealthInfo);

        List<AnimalImg> animalImgList = animalImgMapper.selectListByAnimalId(id);
        animal.setAnimalImgList(animalImgList);
        return animal;
    }

    @Override
    public IPage<Animal> getSearchAnimalInfoList(Page<Animal> animalPage, Animal animalParam) {
        QueryWrapper<Animal> queryWrapper = new QueryWrapper<>();
        if (animalParam != null) {
            if (StringUtils.hasText(animalParam.getProvince()))
                queryWrapper.eq("province", animalParam.getProvince());
            if (StringUtils.hasText(animalParam.getCity()))
                queryWrapper.eq("city", animalParam.getCity());
            if (animalParam.getAge() != null && animalParam.getAge() > 0)
                queryWrapper.eq("age", animalParam.getAge());
            if (StringUtils.hasText(animalParam.getName()))
                queryWrapper.like("name", "%" + animalParam.getName() + "%");
        }
        queryWrapper.eq("animalState", "search");
        queryWrapper.eq("state", "1");
        List<Animal> animals = animalInfoMapper.selectList(animalPage, queryWrapper);
        for (Animal animal : animals) {
            AnimalHealthInfo animalHealthInfo = animalHealthInfoMapper.selectByAnimalId(animal.getId());
            animal.setAnimalHealthInfo(animalHealthInfo);

            List<AnimalImg> animalImgList = animalImgMapper.selectListByAnimalId(animal.getId());
            animal.setAnimalImgList(animalImgList);
        }
        return animalPage.setRecords(animals);
    }

    @Override
    public IPage<Animal> getSendAnimalInfoList(Page<Animal> animalPage, Animal animalParam) {
        QueryWrapper<Animal> queryWrapper = new QueryWrapper<>();
        if (animalParam != null) {
            if (StringUtils.hasText(animalParam.getProvince()))
                queryWrapper.eq("province", animalParam.getProvince());
            if (StringUtils.hasText(animalParam.getCity()))
                queryWrapper.eq("city", animalParam.getCity());
            if (animalParam.getAge() != null && animalParam.getAge() > 0)
                queryWrapper.eq("age", animalParam.getAge());
            if (StringUtils.hasText(animalParam.getName()))
                queryWrapper.like("name", "%" + animalParam.getName() + "%");
        }
        queryWrapper.eq("animalState", "send");
        queryWrapper.eq("state", "1");
        List<Animal> animals = animalInfoMapper.selectList(animalPage, queryWrapper);
        for (Animal animal : animals) {
            AnimalHealthInfo animalHealthInfo = animalHealthInfoMapper.selectByAnimalId(animal.getId());
            animal.setAnimalHealthInfo(animalHealthInfo);

            List<AnimalImg> animalImgList = animalImgMapper.selectListByAnimalId(animal.getId());
            animal.setAnimalImgList(animalImgList);
        }
        return animalPage.setRecords(animals);
    }

    @Override
    public boolean approvalAnimal(Long animalId) {
        Animal animal = new Animal().setState(1);
        animal.setId(animalId);
        boolean b = updateById(animal);
        return b;
    }

    @Override
    public boolean rejectAnimal(Long animalId) {
        Animal animal = new Animal().setState(-1);
        animal.setId(animalId);
        boolean b = updateById(animal);
        return b;
    }

    @Override
    public List<Animal> animalList(Page<Animal> animalPage) {
        List<Animal> animalList = animalInfoMapper.selectList(animalPage, new QueryWrapper<>());

        for (Animal animal : animalList) {
            AnimalHealthInfo animalHealthInfo = animalHealthInfoMapper.selectByAnimalId(animal.getId());
            animal.setAnimalHealthInfo(animalHealthInfo);

            List<AnimalImg> animalImgList = animalImgMapper.selectListByAnimalId(animal.getId());
            animal.setAnimalImgList(animalImgList);
        }
        return animalList;
    }
}
