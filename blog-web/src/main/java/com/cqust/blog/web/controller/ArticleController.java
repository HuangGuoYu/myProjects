package com.cqust.blog.web.controller;

import com.cqust.blog.common.dto.ArticleCategoryDTO;
import com.cqust.blog.common.entity.Article;
import com.cqust.blog.common.entity.ArticleCategory;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.ServletUtils;
import com.cqust.blog.service_api.web.ArticleService;
import com.cqust.blog.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2018/3/24.
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired private ArticleService articleService;

    /**
     * 文章分类添加
     * @param category 分类实体
     * @return 处理结果
     */
    @RequestMapping("/addCategory")
    @ResponseBody
    public GeneralResult<Boolean> addCategory(ArticleCategory category) {
        User userInfo = ServletUtils.getUserInfo(request);
        return articleService.addCategory(category, userInfo);
    }

    /**
     * 编辑分类
     * @param category 分类实体
     * @return 处理结果
     */
    @RequestMapping("/editCategory")
    @ResponseBody
    public GeneralResult<?> editCategory(ArticleCategory category) {
        User userInfo = ServletUtils.getUserInfo(request);
        return articleService.editCategory(category, userInfo);
    }

    /**
     * 删除分类
     * @param category 分类
     * @return 处理结果
     */
    @RequestMapping("/delCategory")
    @ResponseBody
    public GeneralResult<?> delCategory(ArticleCategory category) {
        User userInfo = ServletUtils.getUserInfo(request);
        return articleService.delCategory(category, userInfo);
    }

    /**
     * 根据用户查询所有的分类
     * @return 分类集合数据
     */
    @RequestMapping("/allCate")
    @ResponseBody
    public GeneralResult<List<ArticleCategoryDTO>> queryAllCategoryByUser() {
        return articleService.queryAllCategoryByUser(getSessionUser());
    }

    /**
     * 添加文章
     * @param article 文章实体
     * @return 处理结果
     */
    @RequestMapping("/addArticle")
    @ResponseBody
    public GeneralResult<?> addArticle(Article article) {
        User sessionUser = getSessionUser();
        return articleService.addArticle(article, sessionUser);
    }

    /**
     * 修改文章
     * @param article 文章实体
     * @return 处理结果
     */
    @RequestMapping("/editArticle")
    @ResponseBody
    public GeneralResult<?> editArticle(Article article) {
        User sessionUser = getSessionUser();
        return articleService.editArticle(article, sessionUser);
    }

    /**
     * 修改文章页面
     * @param id id
     * @return 视图名称
     */
    @RequestMapping("/editArticlePage")
    @ResponseBody
    public String editArticlePage(Integer id) {
        User sessionUser = getSessionUser();
        GeneralResult result = articleService.findArticlecDataById(id, sessionUser.getId());
        request.setAttribute("data", result.getData());
        return "editArticlePage";
    }

    /**
     * 文章状态删除
     * @param id id
     * @return 处理结果
     */
    @RequestMapping("/delArticle")
    @ResponseBody
    public GeneralResult delArticle(Integer id) {
        User sessionUser = getSessionUser();
        return articleService.delArticle(id, sessionUser.getId());
    }

    /**
     * 文章删除
     * @param id id
     * @return 处理结果
     */
    @RequestMapping("/delArticleFromDB")
    @ResponseBody
    public GeneralResult delArticleFromDB(Integer id) {
        User sessionUser = getSessionUser();
        return articleService.delArticleFromDB(id, sessionUser.getId());
    }

    /**
     * 将文章重回收站中恢复
     * @param id id
     * @return 处理结果
     */
    @RequestMapping("/renewArticle")
    @ResponseBody
    public GeneralResult renewArticle(Integer id) {
        User sessionUser = getSessionUser();
        return articleService.renewArticle(id, sessionUser.getId());
    }

    @RequestMapping("/writeArticle")
    public String index() {
        User sessionUser = getSessionUser();
        List<ArticleCategory> cates = articleService.queryCateByUserId(sessionUser);
        request.setAttribute("cates", cates);
        request.setAttribute("data", ServletUtils.getUserInfo(request));
        return "writeArticle";
    }


    @RequestMapping("/cateManager")
    public String cateManager() {
        User sessionUser = getSessionUser();
        List<ArticleCategory> cates = articleService.queryCateByUserId(sessionUser);
        request.setAttribute("cates", cates);
        request.setAttribute("data", ServletUtils.getUserInfo(request));
        return "cateManager";
    }

    @RequestMapping("/articleManager")
    public String articleManager() {
        request.setAttribute("data", ServletUtils.getUserInfo(request));
        return "articleManager";
    }
}
