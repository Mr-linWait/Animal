package com.hellen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hellen.entity.client.Animal;
import com.hellen.entity.client.CollectAnimals;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollecanimalsMapper extends BaseMapper<CollectAnimals> {
    List<Animal> getCollectionList(@Param("id") Long id);
}
