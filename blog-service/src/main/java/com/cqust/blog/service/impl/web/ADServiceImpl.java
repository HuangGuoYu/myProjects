package com.cqust.blog.service.impl.web;

import com.cqust.blog.common.entity.Advertisement;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.dao.mappers.AdvertisementMapper;
import com.cqust.blog.service_api.web.ADService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/25.
 */
@Service
public class ADServiceImpl implements ADService {

    @Autowired private AdvertisementMapper advertisementMapper;

    @Override
    public GeneralResult<?> adList(Integer num) {
        GeneralResult result = new GeneralResult();
        List<Advertisement> datas = advertisementMapper.selectByOrd(num);
        return result.ok(datas);
    }
}
