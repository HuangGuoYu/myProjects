package com.cqust.blog.service.impl.web;

import com.cqust.blog.common.dto.ArticleCategoryDTO;
import com.cqust.blog.common.dto.PageEntityDTO;
import com.cqust.blog.common.entity.*;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.DataUtils;
import com.cqust.blog.dao.mappers.ArticleCategoryMapper;
import com.cqust.blog.dao.mappers.ArticleMapper;
import com.cqust.blog.dao.mappers.UserMapper;
import com.cqust.blog.dao.mappers.UserStaticMapper;
import com.cqust.blog.service_api.web.ArticleService;
import com.cqust.blog.service_api.web.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/3/24.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired private ArticleCategoryMapper articleCategoryMapper;

    @Autowired private ArticleMapper articleMapper;

    @Autowired private UserMapper userMapper;

    @Autowired private UserStaticMapper userStaticMapper;

    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

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
            ArticleCategory dbEntity = articleCategoryMapper.checkIsExists(category.getName(), userInfo.getId());
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
        dbEntity = articleCategoryMapper.checkIsExists(category.getName(), userInfo.getId());
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
        //删除分类下的所有文章
        articleMapper.delArticleToDelStateAndCateTo0(userInfo.getId(), dbEntity.getId());
        articleCategoryMapper.deleteByPrimaryKey(category.getId());
        result.setMsg("操作成功");
        return result;
    }

    @Override
    public boolean isContainIllegalInfo(String ... name) {
        //是否包含非法信息
        Map<String, Object> illegalWords = userMapper.findIllegalWords();
        Object wordsObj = illegalWords.get("words");
        if (wordsObj != null) {
            String[] words = wordsObj.toString().split(",");
            for (String word : words) {
                for (String s : name) {
                    if (s.contains(word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public GeneralResult<?> addArticle(Article article, User sessionUser) {
        GeneralResult result = new GeneralResult();
        if (isContainIllegalInfo(article.getTitle(), article.getContent())) {
            return result.error(401, "不可包含非法内容");
        }
        //用户信息初始化
        if (article == null) {
            return result.error(201, "参数不可为空");
        }
        article.setUserId(sessionUser.getId());
        User user = userMapper.selectByPrimaryKey(sessionUser.getId());
        if (user == null || user.getState() != 2) {
            return result.error(404, "当前用户不存在");
        }
        try {
            //数据校验
            GeneralResult<Boolean> dataCheck = DataUtils.checkFieldByAnnotaion(article);
            if (dataCheck.getData()) {
                return dataCheck;
            }
            //初始化相关数据
            article.setPostTime(new Date());
            article.setState((byte) 0);
            //执行保存, 判断是否存在id， 存在则是更新 否则是保存
            if (article.getId() == null) {
                articleMapper.insertSelective(article);
            } else {
                articleMapper.updateByPrimaryKeySelective(article);
            }
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

    @Override
    public GeneralResult likeArticle(Integer aid, User sessionUser) {
        GeneralResult result = new GeneralResult();
        String key = "likeArticle" + ":" + sessionUser.getId() + ":" + aid;
        String check = redisTemplate.opsForValue().get(key);
        if (!DataUtils.strIsNullOrEmpty(check)) {
            return result.error(401, "每天只能点赞一次，24小时后可再次点赞！");
        }
        //执行数据库数据修改
        Article article = articleMapper.selectByPrimaryKey(aid);
        if (article == null) {
            return result.error(404, "没有当前的文章存在");
        }
        article.setLikeNum(article.getLikeNum() + 1);
        articleMapper.updateByPrimaryKey(article);
        //写入缓存
        redisTemplate.opsForValue().set(key, "yes", 1, TimeUnit.DAYS);
        result.setMsg("操作成功");
        return result;
    }

    @Override
    public void execBrowseNum(User sessionUser, String clientIpAddr, Integer aid) {
        String key = String.format("article:%s:%d", clientIpAddr, aid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(new Date());
        String str = redisTemplate.opsForValue().get(key);
        if (str == null) {
            redisTemplate.opsForValue().set(key, clientIpAddr,6, TimeUnit.HOURS);
            Article article = articleMapper.checkArticleByState(aid);
            if (article != null) {
                //用户统计信息
                UserStatic userStatic = userStaticMapper.findStaticByAidAndDate(aid, dateStr);
                if (userStatic == null) {
                    userStatic = new UserStatic();
                    userStatic.setUserId(article.getUserId());
                    userStatic.setTime(dateStr);
                    userStatic.setBrowseNum(1);
                    userStaticMapper.insert(userStatic);
                } else {
                    if (userStatic.getBrowseNum() == null) {
                        userStatic.setBrowseNum(1);
                    } else {
                        userStatic.setBrowseNum(userStatic.getBrowseNum() + 1);
                    }
                    userStaticMapper.updateByPrimaryKeySelective(userStatic);
                }
                //当前文章浏览量添加
                article.setBrowseNum(article.getBrowseNum() == null ? 1 : (article.getBrowseNum() + 1));
                articleMapper.updateByPrimaryKeySelective(article);
            }
        }
    }

    @Override
    public GeneralResult queryArticleData(Integer id) {
        GeneralResult result = new GeneralResult();
        if (id == null) {
            result.error(201, "参数错误");
            return result;
        }
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null || article.getState() != 1 || article.getIsDelete() ==1) {
            return result.error(404, "未找到相关文章");
        }
        Map<String, Object> user = userMapper.selectByPrimaryKeyForArticleDetail(article.getUserId());
        Map<String, Object> datas = new HashMap<>();
        User articleUser = new User();
        articleUser.setId(article.getUserId());
        datas.put("article", article);
        datas.put("user", user);
        UserDetail userDetailInfo = userService.findUserDetailInfo(articleUser);
        datas.put("uinfo", userDetailInfo);
        result.setData(datas);
        return result;
    }

    @Autowired private UserService userService;

    @Override
    public GeneralResult<PageEntityDTO<Article>> queryArticleByState(Integer state, Integer curPage) {
        GeneralResult result = new GeneralResult();
        byte articleState = 0, delState = 0;
        if (state == null || curPage == null) {
            state = 1;
            curPage = 1;
        }

        switch (state) {
            case 0: //审核中，状态未删除
                articleState = 0;
                delState = 0;
                break;
            case 1: //已发表，未删除
                articleState = 1;
                delState = 0;
                break;
            case 2: //回收站，已删除
                articleState = 1;
                delState = 1;
                break;
            case 3: //审核失败的需要充行编辑
                articleState = 2;
                delState = 0;
                break;
        }
        //查询总数
        List<Article> counts = articleMapper.queryListByStateForCount(articleState, delState);
        //查询实体数据
        List<Article> datas = articleMapper.queryListByState(articleState, delState, (curPage - 1) * 10);
        PageEntityDTO<Article> pageEntityDTO = new PageEntityDTO<>(datas);
        pageEntityDTO.setCurPage(curPage);
        pageEntityDTO.setPageCount(Math.ceil(counts.size() * 1.0 / 10));
        result.setData(pageEntityDTO);
        return result;
    }

    @Override
    public GeneralResult<List<ArticleCategoryDTO>> queryAllCategoryByUser(User sessionUser) {
        List<ArticleCategoryDTO> datas = articleCategoryMapper.queryCateByUserIdFroDTO(sessionUser.getId());
        GeneralResult result = new GeneralResult();
        result.ok(datas);
        return result;
    }

    @Override
    public List<ArticleCategory> queryCateByUserId(User sessionUser) {
        List<ArticleCategory> datas = articleCategoryMapper.queryCateByUserId(sessionUser.getId());
        return datas;
    }

    @Override
    public GeneralResult delArticleFromDisk(User sessionUser, Integer id) {
        articleMapper.deleteByPrimaryKey(id);
        GeneralResult result = new GeneralResult();
        return result.ok("删除成功");
    }

    @Override
    public GeneralResult queryArticleForIndex(String title) {
        GeneralResult result = new GeneralResult();
        List<Map<String, Object>> datas = articleMapper.queryListForIndex(title);
        return result.ok(datas);
    }
}
