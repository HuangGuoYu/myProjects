package com.cqust.blog.manager.controller.service.impl;

import com.cqust.blog.common.entity.Advertisement;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.DataUtils;
import com.cqust.blog.manager.controller.dao.BaseDao;
import com.cqust.blog.manager.controller.service.StaticFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public GeneralResult delWords(Integer id) {
        GeneralResult result = new GeneralResult();
        String sql = "delete from tbl_illegal_words where id=:id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        try {
            baseDao.execUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
            return result.ok(201, "操作失败");
        }
        return result.ok(200, "操作成功");
    }

    @Override
    public GeneralResult findAllWords() {
        GeneralResult result = new GeneralResult();
        String sql = "select * from tbl_illegal_words";
        List<Map<String, Object>> bySql = baseDao.findBySql(sql, null);
        result.setData(bySql);
        result.setCode(200);
        return result;
    }

    @Override
    public GeneralResult addWords(String content) {
        GeneralResult result = new GeneralResult();
        if (DataUtils.strIsNullOrEmpty(content)) {
            return result.ok(201, "参数错误");
        }
        Map<String, Object> params = new HashMap<>();
        String checksql = "select * from tbl_illegal_words where name = :name";
        params.put("name", content);
        List<Map<String, Object>> bySql = baseDao.findBySql(checksql, params);
        if(bySql.size() != 0) {
            return result.ok(201, "当前词汇已存在");
        }
        params.clear();
        String sql = "insert into tbl_illegal_words(name) select :content";
        params.put("content", content);
        try {
            baseDao.execUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
            return result.ok(500, "操作失败");
        }

        return result.ok(200, "添加成功");
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
