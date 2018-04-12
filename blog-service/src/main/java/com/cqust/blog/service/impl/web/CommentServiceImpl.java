package com.cqust.blog.service.impl.web;

import com.cqust.blog.common.entity.Article;
import com.cqust.blog.common.entity.ArticleComment;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.DataUtils;
import com.cqust.blog.dao.mappers.ArticleCommentMapper;
import com.cqust.blog.dao.mappers.ArticleMapper;
import com.cqust.blog.service_api.web.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/11.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired private ArticleCommentMapper articleCommentMapper;

    @Autowired private ArticleMapper articleMapper;

    @Override
    public GeneralResult queryDataByAid(String aid) {
        GeneralResult result = new GeneralResult();
        List<Map<String, Object>> datas = articleCommentMapper.queryDataByAid(aid);
        result.setData(datas);
        return result;
    }

    @Override
    public GeneralResult addComemnt(String content, Integer aid, User sessionUser) {
        GeneralResult result = new GeneralResult();
        if (DataUtils.strIsNullOrEmpty(content) || aid == null) {
            return result.error(201, "内容不可为空");
        }
        //判断文章是否存在
        Article article = articleMapper.checkArticleByState(aid);
        if (article == null) {
            return result.error(401,"非法操作");
        }
        article.setCommentNum(article.getCommentNum() + 1);
        articleMapper.updateByPrimaryKey(article);
        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticleId(aid);
        articleComment.setCommentTime(new Date());
        articleComment.setCuserId(sessionUser.getId());
        articleComment.setContent(content);
        articleComment.setState((byte) 1);
        articleCommentMapper.insert(articleComment);
        result.setMsg("操作成功");
        return result;
    }
}
