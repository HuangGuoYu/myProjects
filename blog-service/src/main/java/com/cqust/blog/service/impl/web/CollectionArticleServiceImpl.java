package com.cqust.blog.service.impl.web;

import com.cqust.blog.common.entity.Article;
import com.cqust.blog.common.entity.ArticleUserRel;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.entity.UserStatic;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.dao.mappers.ArticleMapper;
import com.cqust.blog.dao.mappers.ArticleUserRelMapper;
import com.cqust.blog.dao.mappers.UserStaticMapper;
import com.cqust.blog.service_api.web.CollectionArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/11.
 */
@Service
public class CollectionArticleServiceImpl implements CollectionArticleService {

    @Autowired private ArticleUserRelMapper articleUserRelMapper;

    @Autowired private ArticleMapper articleMapper;

    @Autowired private UserStaticMapper userStaticMapper;

    @Override
    @Transactional
    public GeneralResult addCollection(User sessionUser, Integer aid) {
        GeneralResult result = new GeneralResult();
        if (aid == null) {
            return result.error(201, "参数错误");
        }
        Article article = articleMapper.checkArticleByState(aid);
        if (article == null) {
            return result.error(404, "未找到你所收藏的文章");
        }
        //判断当前用户是否已经收藏过
        ArticleUserRel checkArticleUserRel = articleUserRelMapper.queryByUidAndAid(sessionUser.getId(), aid);
        if (checkArticleUserRel != null) {
            return result.error(401, "你已经收藏过该文章，到个人中心查看");
        }
        ArticleUserRel articleUserRel = new ArticleUserRel();
        articleUserRel.setArticleId(aid);
        articleUserRel.setUserId(sessionUser.getId());
        articleUserRelMapper.insert(articleUserRel);
        execStaticData(aid, article.getUserId());
        //添加文章本身的数据
        article.setLikeNum(article.getLikeNum() == null ? 1 : (article.getLikeNum() + 1));
        result.setMsg("收藏成功，到个人中心查看");
        return result;
    }


    /**
     * 为被收藏文章的用户添加统计数据
     */
    private void execStaticData(Integer aid, Integer auser) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(new Date());
        UserStatic userStatic = userStaticMapper.findStaticByAidAndDate(aid, dateStr);
        if (userStatic == null) {
            userStatic = new UserStatic();
            userStatic.setLikeNum(1);
            userStatic.setUserId(auser);
            userStatic.setTime(dateStr);
            userStaticMapper.insert(userStatic);
        } else {
            if (userStatic.getLikeNum() == null) {
                userStatic.setLikeNum(1);
            } else {
                userStatic.setBrowseNum(userStatic.getLikeNum() + 1);
            }
            userStaticMapper.updateByPrimaryKey(userStatic);
        }
    }
}
