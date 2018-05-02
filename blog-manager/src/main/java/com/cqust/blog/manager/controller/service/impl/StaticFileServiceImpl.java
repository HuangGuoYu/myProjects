package com.cqust.blog.manager.controller.service.impl;

import com.cqust.blog.common.entity.Advertisement;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.DataUtils;
import com.cqust.blog.manager.controller.dao.BaseDao;
import com.cqust.blog.manager.controller.service.StaticFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */
@Component
public class StaticFileServiceImpl implements StaticFileService {

    @Autowired private BaseDao baseDao;

    @Override
    public GeneralResult execSaveAd(Advertisement advertisement) {
        GeneralResult result = new GeneralResult();
        try {
            GeneralResult<Boolean> cheRes = DataUtils.checkFieldByAnnotaion(advertisement);
            advertisement.setState((byte) 1);
            if (cheRes.getData()) {
                return cheRes;
            }
            baseDao.execEntitySave(advertisement);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result.ok("添加成功");
    }

    @Override
    public GeneralResult findAdList() {
        GeneralResult result = new GeneralResult();
        List<Advertisement> datas = baseDao.findAllEntityByClass(Advertisement.class);
        return result.ok(datas);
    }

    @Override
    public GeneralResult execUpdateState(Integer id) {
        GeneralResult result = new GeneralResult();
        if (id == null) {
            return result.error(201, "参数错误");
        }
        Advertisement dbEntity = baseDao.findEntityById(id, Advertisement.class);
        if (dbEntity == null) {
            return result.error(404, "不存在当前用户");
        }
        dbEntity.setState((byte) 0);
        baseDao.execEntityUpdate(dbEntity);
        return result.ok("操作成功");
    }

    @Override
    public GeneralResult execadApproval(Integer id) {
        GeneralResult result = new GeneralResult();
        if (id == null) {
            return result.error(201, "参数错误");
        }
        Advertisement dbEntity = baseDao.findEntityById(id, Advertisement.class);
        if (dbEntity == null) {
            return result.error(404, "不存在当前用户");
        }
        dbEntity.setState((byte) 1);
        baseDao.execEntityUpdate(dbEntity);
        return result.ok("操作成功");
    }
}
