package com.hellen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hellen.entity.client.Animal;
import com.hellen.entity.client.CollectAnimals;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollecanimalsMapper extends BaseMapper<CollectAnimals> {
}
