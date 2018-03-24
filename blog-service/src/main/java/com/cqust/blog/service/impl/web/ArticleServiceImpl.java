package com.cqust.blog.service.impl.web;

import com.cqust.blog.common.entity.ArticleCategory;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.DataUtils;
import com.cqust.blog.dao.mappers.ArticleCategoryMapper;
import com.cqust.blog.service_api.web.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/3/24.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired private ArticleCategoryMapper articleCategoryMapper;

    @Override
    public GeneralResult<Boolean> addCategory(ArticleCategory category, User userInfo) {
        GeneralResult<Boolean> result = new GeneralResult<>();
        category.setUserId(userInfo.getId());
        try {
            //数据合法性检验
            result = DataUtils.checkFieldByAnnotaion(category);
            if (result.getData()) {
                return result;
            }
            //分类是否存在
            ArticleCategory dbEntity = articleCategoryMapper.checkIsExists(category.getName());
            if (dbEntity != null) {
                result.setCode(401);
                result.setMsg("该分类已存在");
                return result;
            }
            //分类不存在添加分类
            articleCategoryMapper.insertSelective(category);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("数据监测异常");
            return result;
        }
        result.setMsg("添加成功");
        result.setData(true);
        return result;
    }

    @Override
    public GeneralResult<?> editCategory(ArticleCategory category, User userInfo) {
        category.setUserId(userInfo.getId());
        GeneralResult result = new GeneralResult();
        //判断是否传递了ID
        if (DataUtils.strIsNullOrEmpty(String.valueOf(category.getId()), category.getName())) {
            result.setCode(201);
            result.setMsg("参数错误");
            return result;
        }
        //判断是否存在当前分类
        ArticleCategory dbEntity = articleCategoryMapper.editCheckIsExists(category.getId(), userInfo.getId());
        if (dbEntity == null) {
            result.setCode(404);
            result.setMsg("没有找到当前分类");
            return result;
        }
        //检测修改后的名称是否存在
        dbEntity = articleCategoryMapper.checkIsExists(category.getName());
        if (dbEntity != null) {
            result.setMsg("当前名称分类已存在，不可修改");
            result.setCode(401);
            return result;
        }
        //执行分类修改
        articleCategoryMapper.updateByPrimaryKeySelective(category);
        result.setCode(200);
        result.setMsg("修改成功");
        return result;
    }

    @Override
    public GeneralResult<?> delCategory(ArticleCategory category, User userInfo) {
        GeneralResult result = new GeneralResult();
        if (DataUtils.strIsNullOrEmpty(String.valueOf(category.getId()))) {
            result.setCode(201);
            result.setMsg("参数错误");
            return result;
        }
        ArticleCategory dbEntity = articleCategoryMapper.editCheckIsExists(category.getId(), userInfo.getId());
        if (dbEntity == null) {
            result.setCode(401);
            result.setMsg("不存在当前分类信息");
            return result;
        }
        articleCategoryMapper.deleteByPrimaryKey(category.getId());
        result.setMsg("操作成功");
        return result;
    }


}
