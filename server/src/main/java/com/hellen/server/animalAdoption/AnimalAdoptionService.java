package com.hellen.server.animalAdoption;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hellen.entity.client.AnimalAdoption;

import java.util.List;

public interface AnimalAdoptionService extends IService<AnimalAdoption> {
    AnimalAdoption getByOtherId(Long userId, Long animalsId);

    void adoptAgain(Long animalId);

    List<AnimalAdoption> getAdoptApplyList();

    void agreeAdopt(Long applyId, Long animalId);

    void reject(Long applyId, Long animalId);
}
