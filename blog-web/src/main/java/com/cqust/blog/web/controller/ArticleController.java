package com.cqust.blog.web.controller;

import com.cqust.blog.common.dto.ArticleCategoryDTO;
import com.cqust.blog.common.dto.PageEntityDTO;
import com.cqust.blog.common.entity.Article;
import com.cqust.blog.common.entity.ArticleCategory;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.entity.UserDetail;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.ServletUtils;
import com.cqust.blog.service_api.web.ADService;
import com.cqust.blog.service_api.web.ArticleService;
import com.cqust.blog.service_api.web.UserService;
import com.cqust.blog.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/24.
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired private ArticleService articleService;

    @Autowired private UserService userService;

    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

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

//    /**
//     * 修改文章页面
//     * @param id id
//     * @return 视图名称
//     */
//    @RequestMapping("/editArticlePage")
//    @ResponseBody
//    public String editArticlePage(Integer id) {
//        User sessionUser = getSessionUser();
//        GeneralResult result = articleService.findArticlecDataById(id, sessionUser.getId());
//        request.setAttribute("data", result.getData());
//        return "editArticlePage";
//    }

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

    @RequestMapping("/delFromDisk")
    @ResponseBody
    public GeneralResult delFromDisk(Integer id) {
        articleService.delArticleFromDisk(getSessionUser(), id);
        return articleService.delArticleFromDisk(getSessionUser(), id);
    }

    @RequestMapping("/queryArticleByState")
    @ResponseBody
    public GeneralResult<PageEntityDTO<Article>> queryArticleByState(Integer state, Integer curPage) {
        User sessionUser = getSessionUser();
        Integer uid = sessionUser.getId();
        List<Article> remove = new ArrayList<>();
        GeneralResult<PageEntityDTO<Article>> res = articleService.queryArticleByState(state, curPage);
        PageEntityDTO<Article> data = res.getData();
        List<Article> datas = data.getDatas();
        for (Article item : datas) {
            if (!item.getUserId().equals(uid)) {
                remove.add(item);
            }
        }
        datas.removeAll(remove);
        return res;
    }

    @RequestMapping("/queryArticleForIndex")
    @ResponseBody
    public GeneralResult queryArticleForIndex(String title) {
        GeneralResult result = articleService.queryArticleForIndex(title);
        return result;
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
        UserDetail parameter = userService.findUserDetailInfo(getSessionUser());
        request.setAttribute("uinfo", parameter);
        request.setAttribute("isEdit", 0);
        return "writeArticle";
    }

    @RequestMapping("editArticlePage")
    public String editArticle(Integer id) {
        request.setAttribute("isEdit", 1);
        List<ArticleCategory> cates = articleService.queryCateByUserId(getSessionUser());
        request.setAttribute("cates", cates);
        request.setAttribute("data", ServletUtils.getUserInfo(request));
        UserDetail parameter = userService.findUserDetailInfo(getSessionUser());
        request.setAttribute("uinfo", parameter);
        GeneralResult articlecDataById = articleService.findArticlecDataById(id, getSessionUser().getId());
        request.setAttribute("article", articlecDataById.getData());
        //如果编辑的不是自己的页面
        if (articlecDataById.getData() == null) {
            return "_404";
        }
        return "writeArticle";
    }


    @RequestMapping("/cateManager")
    public String cateManager() {
        User sessionUser = getSessionUser();
        List<ArticleCategory> cates = articleService.queryCateByUserId(sessionUser);
        request.setAttribute("cates", cates);
        request.setAttribute("data", ServletUtils.getUserInfo(request));
        UserDetail parameter = userService.findUserDetailInfo(getSessionUser());
        request.setAttribute("uinfo", parameter);
        return "cateManager";
    }

    @RequestMapping("/articleManager")
    public String articleManager() {
        request.setAttribute("data", ServletUtils.getUserInfo(request));
        //获得用户的详细信息
        UserDetail parameter = userService.findUserDetailInfo(getSessionUser());
        request.setAttribute("uinfo", parameter);
        return "articleManager";
    }

    @Autowired private ADService adService;
    /**
     * 同步返回到文章详情页
     * @param id 文章id
     * @return 处理结果
     */
    @RequestMapping("/articleDetail")
    public String articleDetail(Integer id) {
        GeneralResult result = articleService.queryArticleData(id);
        request.setAttribute("ad", adService.adList(5).getData());
        if (result.getCode() != 200) {
            return "_404";
        }
        //判断是否增加浏览量
        articleService.execBrowseNum(getSessionUser(), getClientIpAddr(request), id);
        User user = getSessionUser();
        //是否登录状态
        request.setAttribute("isLogin", user == null ? 0 : 1);
        //数据
        request.setAttribute("data", result.getData());
        //是否点赞
        if (user == null) {
            request.setAttribute("likeState", 0);
        } else {
            String key = "likeArticle:" + user.getId() + ":" + id;
            String state = redisTemplate.opsForValue().get(key);
            if (state == null) {
                request.setAttribute("likeState", 0);
            } else {
                request.setAttribute("likeState", 1);
            }
        }

        return "articleDetail";
    }

    /**
     * 根据文章id 查询相关数据
     * @param id 文章id
     * @return 数据集
     */
    @RequestMapping("/articleData")
    @ResponseBody
    public GeneralResult articleData(Integer id) {
        return articleService.queryArticleData(id);
    }

    /**
     * 文章点赞每天一次
     * @param aid 文章id
     * @return 处理结果
     */
    @RequestMapping("/likeArticle")
    @ResponseBody
    public GeneralResult likeArticle(Integer aid) {
        return articleService.likeArticle(aid, getSessionUser());
    }
}
