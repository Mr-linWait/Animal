package com.hellen.server.collecanimals;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hellen.entity.client.Animal;
import com.hellen.entity.client.CollectAnimals;

import java.util.List;

public interface CollecanimalsService extends IService<CollectAnimals> {

    CollectAnimals getByOtherId(Long userId, Long animalsId);

    boolean cancel(CollectAnimals collectAnimals);

    List<CollectAnimals> getCollectionInMessageList(Long userId);

    List<Animal> getCollectionList();


}
