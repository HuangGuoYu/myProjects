package com.cqust.blog.manager.controller.service.impl;

import com.cqust.blog.common.common.ConstantCode;
import com.cqust.blog.common.entity.*;
import com.cqust.blog.manager.controller.dao.BaseDao;
import com.cqust.blog.manager.controller.service.ComputeUserIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 */
@Component
public class ComputeUserIncomeServiceImpl implements ComputeUserIncomeService {

    @Autowired private BaseDao baseDao;

    @Scheduled(cron = "0 0 2 * * ?") //每晚凌晨2点执行
//    @Scheduled(cron = "0/10 * *  * * ? ") //十秒1次
    @Override
    public void compute() {
        Integer income = 0;
        Map<String, Object> params = new HashMap<>();
        //得到系统所有用户
        List<User> users = baseDao.findAllEntityByClass(User.class);
        //循环计算每个用户前日收入
        for (User user : users) {
            params.clear();
            income = 0;
            //用户关注数
            Integer attentionNum = user.getAttentionNum() == null ? 0 : user.getAttentionNum();
            income += (attentionNum / ConstantCode.BASE_ATTENTION) * ConstantCode.BASE_ATTENTION_$;
            //用户所写文章浏览量
            //用户所有文章
            String hql = "from Article where userId = :uid";
            params.put("uid", user.getId());
            List<Article> articles = baseDao.findEntityByHql(hql, params);
            //文章达到一千访问增加3元
            for (Article article : articles) {
                Integer browseNum = article.getBrowseNum() == null ? 0 : article.getBrowseNum();
                income += (browseNum / ConstantCode.BASE_BROWSE) * ConstantCode.BASE_BROWSE_$;
            }
            //计算原创数
            String sql = "SELECT count(0) count from tbl_article where user_id = :uid and is_original = 1";
            List<Map<String, Object>> originals = baseDao.findBySql(sql, params);
            BigInteger count = (BigInteger) originals.get(0).get("count");
            income += (count.intValue() / ConstantCode.BASE_ORIGINAL) * ConstantCode.BASE_ORIGINAL_$;
            //减去之前的收入得到当日收入
            sql = "SELECT SUM(income) agoIncome from tbl_user_static where user_id = :uid ";
            Map<String, Object> agoIncoms = baseDao.findBySql(sql, params).get(0);
            BigDecimal count1  = (BigDecimal) (agoIncoms.get("agoIncome") == null ? BigDecimal.valueOf(0) : agoIncoms.get("agoIncome"));
            income -= count1.intValue();
            //写入当日收入
            Date date = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = format.format(date);
            //查找是否存在记录
            UserStaticKey key = new UserStaticKey(user.getId(), strDate);
            UserStaticHibernate dbStatic = baseDao.findEntityById(key, UserStaticHibernate.class);
            if (dbStatic == null) {
                dbStatic = new UserStaticHibernate();
                dbStatic.setIncome(Long.valueOf(income));
                dbStatic.setTime(strDate);
                dbStatic.setUserId(user.getId());
                baseDao.execEntitySave(dbStatic);
            } else {
                dbStatic.setIncome(Long.valueOf(income));
                baseDao.execEntityUpdate(dbStatic);
            }
            user.setBanlance(BigDecimal.valueOf((user.getBanlance() == null ? BigDecimal.valueOf(0) : user.getBanlance()).intValue() + income));
            baseDao.execEntityUpdate(user);
        }
        System.out.println("************************");
    }

    @Scheduled(cron = "0 0 2 * * ? ")
    @Override
    public void test() {
        System.out.println("好的，执行成功");
    }
}
