package com.cqust.blog.manager.controller.service.impl;

import com.cqust.blog.common.entity.*;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.manager.controller.dao.BaseDao;
import com.cqust.blog.manager.controller.service.ArticleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.GeneratedValue;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/29.
 */
@Service
public class ArticleManagerServiceImpl implements ArticleManagerService {

    @Autowired private BaseDao baseDao;


    @Override
    public GeneralResult findAllArticle() {
        GeneralResult result = new GeneralResult();
        String sql =
                "SELECT a.*,b.blog_name from tbl_article a " +
                        "LEFT JOIN tbl_user b on a.user_id = b.id where is_delete = 0 and a.state != 2 ";
        List<Map<String, Object>> datas = baseDao.findBySql(sql, null);
        return result.ok(200, datas);
    }

    @Override
    public GeneralResult execApproval(Integer aid, Integer uid) {
        GeneralResult result = new GeneralResult();
        Article article = baseDao.findEntityById(aid, Article.class);
        //通过文章的审核
        article.setState((byte) 1);
        Date curDate = new Date();
        article.setCertTime(curDate);
        baseDao.execEntityUpdate(article);
        //添加假用户统计数据
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String curTime = format.format(curDate);
        UserStaticKey staticKey = new UserStaticKey(uid, curTime);
        //添加统计数据
        UserStaticHibernate staticEntity = baseDao.findEntityById(staticKey, UserStaticHibernate.class);
        if (staticEntity == null) {
            staticEntity = new UserStaticHibernate();
            staticEntity.setUserId(uid);
            staticEntity.setTime(curTime);
            staticEntity.setPostNum(1);
            if (article.getIsOriginal() == 1) {
                staticEntity.setOriginalNum(staticEntity.getOriginalNum() == null ? 1 : staticEntity.getOriginalNum() + 1);
            }
            baseDao.execEntitySave(staticEntity);
        } else {
            staticEntity.setPostNum(staticEntity.getPostNum() == null ? 1 : (staticEntity.getPostNum() + 1));
            baseDao.execEntityUpdate(staticEntity);
        }
        return result.ok("审核成功");
    }

    @Override
    public GeneralResult<?> forbid(Integer aid, SysUser sessionSysUser, String note) {
        GeneralResult result = new GeneralResult();
        if (sessionSysUser == null) {
            return result.error(401, "非法操作,会话失效");
        }
        SysUser user = baseDao.findEntityById(sessionSysUser.getId(), SysUser.class);
        if (user == null) {
            return result.error(401, "非法操作,会话失效");
        }
        //文章状态修改
        Article article = baseDao.findEntityById(aid, Article.class);
        if (article == null) {
            return result.error(201, "不存在当前文章");
        }
        article.setState((byte) 2);
        baseDao.execEntityUpdate(article);
        //执行审核记录添加
        String hql = "from ArticleAuthRecord where articleId = :aid";
        Map<String, Object> params = new HashMap<>();
        params.put("aid", aid);
        List<ArticleAuthRecord> articleAuthRecords = baseDao.findEntityByHql(hql, params);
        if (articleAuthRecords.size() != 0) {
            ArticleAuthRecord articleAuthRecord = articleAuthRecords.get(0);
            articleAuthRecord.setAuthUser(sessionSysUser.getId());
            articleAuthRecord.setAuthTime(new Date());
            articleAuthRecord.setNote(note);
            baseDao.execEntityUpdate(articleAuthRecord);
        } else {
            ArticleAuthRecord articleAuthRecord = new ArticleAuthRecord();
            articleAuthRecord.setAuthUser(sessionSysUser.getId());
            articleAuthRecord.setAuthTime(new Date());
            articleAuthRecord.setNote(note);
            articleAuthRecord.setArticleId(aid);
            baseDao.execEntitySave(articleAuthRecord);
        }
        return result.ok(200, "操作成功");
    }
}
