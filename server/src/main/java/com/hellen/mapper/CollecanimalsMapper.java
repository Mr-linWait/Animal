package com.hellen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hellen.entity.client.Animal;
import com.hellen.entity.client.CollectAnimals;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollecanimalsMapper extends BaseMapper<CollectAnimals> {
    IPage<Animal> getCollectionList(Page<Animal> animalPage, String animalState, Long userId);
}
