package com.hellen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hellen.entity.client.AnimalImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnimalImgMapper extends BaseMapper<AnimalImg> {
    int batchInsert(@Param("list") List<AnimalImg> animalImgList,@Param("id") Long id);
}
