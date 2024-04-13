package com.hellen.server.animalInfo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hellen.entity.client.Animal;
import com.hellen.entity.client.AnimalHealthInfo;
import com.hellen.enum_.AnimalState;
import com.hellen.mapper.AnimalHealthInfoMapper;
import com.hellen.mapper.AnimalImgMapper;
import com.hellen.mapper.AnimalInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

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
        if (animalInfo.getAnimalHealthInfo() == null && animalInfo.getAnimalState()== AnimalState.send)
            return false;
        boolean save = save(animalInfo);
        AnimalHealthInfo animalHealthInfo = animalInfo.getAnimalHealthInfo();
        if (animalHealthInfo != null)
            animalHealthInfoMapper.insert(animalHealthInfo.setAnimalId(animalInfo.getId()));
        if (!CollectionUtils.isEmpty(animalInfo.getAnimalImgList()))
            animalImgMapper.batchInsert(animalInfo.getAnimalImgList(), animalInfo.getId());
        return save;
    }
}
