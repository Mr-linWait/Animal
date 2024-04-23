package com.hellen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hellen.entity.client.AnimalAdoption;
import com.hellen.enum_.ApplyState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnimalAdoptionMapper extends BaseMapper<AnimalAdoption> {
    void updateState(@Param("applyId") Long applyId, @Param("animalId") Long animalId, @Param("applyState") ApplyState applyState);

    List<AnimalAdoption> getAdoptApplyList(Long userId);

    void updateStateByAnimalId(@Param("animalId") Long animalId, @Param("applyState") ApplyState applyState);
}
