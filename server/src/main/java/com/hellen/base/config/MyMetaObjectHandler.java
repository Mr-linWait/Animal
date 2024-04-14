package com.hellen.base.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hellen.base.util.UserUtil;
import com.hellen.entity.manangement.User;
import com.hellen.server.user.UserController;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        User user = UserUtil.getUser();
        String modifier ;
        if (user!=null)
            modifier=String.valueOf(user.getId());
        else
            modifier="";

        this.fillStrategy(metaObject, "modifier", modifier);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        User user = UserUtil.getUser();
        String modifier ;
        if (user!=null)
            modifier=String.valueOf(user.getId());
        else
            modifier="";
        this.strictInsertFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
        this.fillStrategy(metaObject, "modifier", modifier);

    }
}
