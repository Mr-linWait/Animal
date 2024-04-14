package com.hellen.server.img;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hellen.entity.client.AnimalImg;
import com.hellen.mapper.ImgMapper;
import org.springframework.stereotype.Service;

@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, AnimalImg> implements ImgService {
}
