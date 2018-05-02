package com.cqust.blog.manager.controller.service.impl;

import com.cqust.blog.common.entity.SysUser;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.DataUtils;
import com.cqust.blog.manager.controller.dao.BaseDao;
import com.cqust.blog.manager.controller.service.SysUserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/1.
 */
@Service
public class SysUserManagerServiceImpl implements SysUserManagerService {

    @Autowired private BaseDao baseDao;

    @Override
    public GeneralResult findAllSysUser(SysUser user) {
        GeneralResult result = new GeneralResult();
        List<SysUser> datas = baseDao.findAllEntityByClass(SysUser.class);
        SysUser temp = null;
        //排除当前自身用户
        for (SysUser data : datas) {
            if (user!= null && data.getId().equals(user.getId())) {
                temp = data;
            }
        }
        datas.remove(temp);
        return result.ok(200, datas);
    }

    @Override
    public GeneralResult editUserPwd(Integer id, String pwd) {
        GeneralResult result = new GeneralResult();
        SysUser user = baseDao.findEntityById(id, SysUser.class);
        if (user == null) {
            return result.error(404, "不存在当前用户");
        }
        user.setPwd(pwd);
        baseDao.execEntityUpdate(user);
        return result.ok(200, "修改成功");
    }

    @Override
    public GeneralResult delUser(SysUser sessionSysUser, Integer id) {
        GeneralResult result = new GeneralResult();
        if (sessionSysUser == null) {
            result.setUrl("/admin/login");
            return result.error(300, "请重新登录在操作");
        }
        if (sessionSysUser.getId() == id) {
            return result.error(401, "不能删除当前用户");
        }
        SysUser userDb = baseDao.findEntityById(id, SysUser.class);
        if (userDb == null) {
            return result.error(404, "不存在当前用户");
        }
        String sql = "delete from tbl_sys_user where id = :uid";
        Map<String, Object> params = new HashMap<>();
        params.put("uid", id);
        baseDao.execUpdate(sql, params);
        return result.ok(200, "操作成功");
    }

    @Override
    public GeneralResult addUser(String name, String pwd) {
        GeneralResult result = new GeneralResult();
        SysUser user = new SysUser();
        user.setPwd(pwd);
        user.setName(name);
        try {
            GeneralResult<Boolean> cheRes = DataUtils.checkFieldByAnnotaion(user);
            if (cheRes.getData()) {
                return cheRes;
            }
            baseDao.execEntitySave(user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return result.error(500, "服务器错误");
        }
        return result.ok(user.getId());
    }

    @Override
    public GeneralResult login(Integer account, String pwd) {
        GeneralResult result = new GeneralResult();
        SysUser user = baseDao.findEntityById(account, SysUser.class);
        if (user == null || !user.getPwd().equals(pwd)) {
            return result.error(404, "用户名或密码错误");
        }
        result.setUrl("/admin/index");
        return result.ok(user);
    }
}
