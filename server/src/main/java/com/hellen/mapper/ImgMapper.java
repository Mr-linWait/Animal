package com.hellen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hellen.entity.client.AnimalImg;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImgMapper extends BaseMapper<AnimalImg> {
}
