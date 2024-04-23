package com.hellen.server.animalAdoption;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hellen.base.util.UserUtil;
import com.hellen.entity.client.AnimalAdoption;
import com.hellen.entity.manangement.User;
import com.hellen.enum_.ApplyState;
import com.hellen.mapper.AnimalAdoptionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalAdoptionServiceImpl extends ServiceImpl<AnimalAdoptionMapper, AnimalAdoption> implements AnimalAdoptionService {

    @Resource
    private AnimalAdoptionMapper animalAdoptionMapper;

    @Override
    public AnimalAdoption getByOtherId(Long userId, Long animalId) {
        AnimalAdoption animalAdoption = new AnimalAdoption();
        animalAdoption.setAnimalId(animalId);
        animalAdoption.setApplyId(userId);

        QueryWrapper<AnimalAdoption> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(animalAdoption);

        return animalAdoptionMapper.selectOne(queryWrapper);
    }

    @Override
    public void adoptAgain(Long animalId) {
        User user = UserUtil.getUser();
        animalAdoptionMapper.updateState(user.getId(), animalId, ApplyState.Applying);
    }

    @Override
    public List<AnimalAdoption> getAdoptApplyList() {
        return animalAdoptionMapper.getAdoptApplyList(UserUtil.getUser().getId());
    }

    @Override
    public void agreeAdopt(Long applyId, Long animalId) {
        animalAdoptionMapper.updateStateByAnimalId(animalId, ApplyState.OTHER_PASS);
        animalAdoptionMapper.updateState(applyId, animalId, ApplyState.Pass);
    }

    @Override
    public void reject(Long applyId, Long animalId) {
        animalAdoptionMapper.updateState(applyId, animalId, ApplyState.REJECT);
    }
}
