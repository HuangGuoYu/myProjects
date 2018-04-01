package com.cqust.blog.service.impl.web;

import com.cqust.blog.common.entity.Article;
import com.cqust.blog.common.entity.ArticleCategory;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.DataUtils;
import com.cqust.blog.dao.mappers.ArticleCategoryMapper;
import com.cqust.blog.dao.mappers.ArticleMapper;
import com.cqust.blog.service_api.web.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/24.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired private ArticleCategoryMapper articleCategoryMapper;

    @Autowired private ArticleMapper articleMapper;

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

    @Override
    public GeneralResult<?> addArticle(Article article, User sessionUser) {
        GeneralResult result = new GeneralResult();
        //用户信息初始化
        if (article == null) {
            return result.error(201, "参数不可为空");
        }
        article.setUserId(sessionUser.getId());

        try {
            //数据校验
            GeneralResult<Boolean> dataCheck = DataUtils.checkFieldByAnnotaion(article);
            if (dataCheck.getData()) {
                return dataCheck;
            }
            //初始化相关数据
            article.setPostTime(new Date());
            article.setState((byte) 0);
            //执行保存
            articleMapper.insertSelective(article);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return result.error(500, "服务器内部错误");
        }
        return result.ok("操作成功");
    }

    @Override
    public GeneralResult<?> editArticle(Article article, User sessionUser) {
        GeneralResult result = new GeneralResult();
        if (article.getId() == null) {
            return result.error(201, "参数错误");
        }
        //判断当前修改文章是否是属于当前用户以及文章更是否存在
        Article dbData = articleMapper.selectByUserIdAndArticleId(article.getId(), sessionUser.getId());
        if (dbData == null) {
            return result.error(404, "当前修改文章不存在");
        }
        //设置当前的修改
        dbData.setTitle(article.getTitle());
        dbData.setContent(article.getContent());
        dbData.setCateId(article.getCateId());
        int i = articleMapper.updateByPrimaryKey(dbData);
        return result.ok("操作成功");
    }

    @Override
    public GeneralResult findArticlecDataById(Integer aid, Integer uid) {
        GeneralResult result = new GeneralResult();
        if (aid == null) {
            return result.error(201, "参数错误");
        }
        Article article = articleMapper.selectByUserIdAndArticleId(aid, uid);
        if (article == null) {
            result.error(404, "没有找到对应的数据");
        }
        result.setData(article);
        return result;
    }

    @Override
    public GeneralResult delArticle(Integer aid, Integer uid) {
        GeneralResult result = new GeneralResult();
        if (aid == null) {
            return result.error(201, "参数错误");
        }
        Article article = articleMapper.selectByUserIdAndArticleId(aid, uid);
        if (article == null) {
            result.error(404, "没有找到对应的数据");
        }
        article.setIsDelete((byte) 1);
        articleMapper.updateByPrimaryKey(article);
        return result.ok("删除成功，当前保存在回收站");
    }

    @Override
    public GeneralResult delArticleFromDB(Integer aid, Integer uid) {
        GeneralResult result = new GeneralResult();
        if (aid == null) {
            return result.error(201, "参数错误");
        }
        Article article = articleMapper.selectByUserIdAndArticleId(aid, uid);
        if (article == null) {
            result.error(404, "没有找到对应的数据");
        }
        articleMapper.deleteByPrimaryKey(aid);
        return result.ok("删除成功");
    }

    @Override
    public GeneralResult renewArticle(Integer aid, Integer uid) {
        GeneralResult result = new GeneralResult();
        if (aid == null) {
            return result.error(201, "参数错误");
        }
        Article article = articleMapper.selectByUserIdAndArticleId(aid, uid);
        if (article == null) {
            result.error(404, "没有找到对应的数据");
        }
        article.setIsDelete((byte) 0);
        articleMapper.updateByPrimaryKey(article);
        return result.ok("成功回收文章");
    }
}
