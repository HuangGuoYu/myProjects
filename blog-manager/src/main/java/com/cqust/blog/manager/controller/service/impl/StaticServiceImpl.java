package com.cqust.blog.manager.controller.service.impl;

import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.DataUtils;
import com.cqust.blog.manager.controller.dao.BaseDao;
import com.cqust.blog.manager.controller.service.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/1.
 */
@Service
public class StaticServiceImpl implements StaticService {

    @Autowired private BaseDao baseDao;

    @Override
    public GeneralResult findUserRegData() {
        String sql =
                "SELECT date(reg_time) time,count(date(reg_time)) count from tbl_user \n" +
                "where DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= date(reg_time)\n" +
                "GROUP BY time\n" +
                "ORDER BY time";
        List<Map<String, Object>> datas = baseDao.findBySql(sql, null);
        Map<String, Integer> map = DataUtils.convertListToMap(datas,"time", "count");
        String[] xs = new String[7];
        Integer[] xds = new Integer[7];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM-dd");
        Date cur = new Date();
        for (int i = 6; i >= 0; i--) {
            xs[i] = simpleDateFormat.format(cur.getTime() - i * (24 * 60 * 60 * 1000));
            if (map.containsKey(xs[i])) {
                xds[i] = map.get(xs[i]);
            } else {
                xds[i] = 0;
            }
            xs[i] = simpleDateFormat2.format(cur.getTime() - i * (24 * 60 * 60 * 1000));
        }
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("xs", xs);
        resMap.put("xds", xds);
        GeneralResult result = new GeneralResult();
        return result.ok(resMap);
    }

    @Override
    public GeneralResult findArticleData() {
        String sql = "SELECT date(post_time) time, count(date(post_time)) count from tbl_article where \n" +
                "DATE_SUB(CURDATE(),INTERVAL 6 DAY) <= date(post_time)\n" +
                "GROUP BY time\n" +
                "ORDER BY time";
        List<Map<String, Object>> datas = baseDao.findBySql(sql, null);
        Map<String, Integer> map = DataUtils.convertListToMap(datas,"time", "count");
        Map<String, Object> resMap = timeStatic(map);
        GeneralResult result = new GeneralResult();
        return result.ok(resMap);
    }

    @Override
    public GeneralResult findArticleCertData() {
        String sql = "SELECT date(post_time) time, count(date(post_time)) count from tbl_article where \n" +
                "DATE_SUB(CURDATE(),INTERVAL 6 DAY) <= date(post_time)\n" +
                "and state = 1\n" +
                "GROUP BY time\n" +
                "ORDER BY time";
        List<Map<String, Object>> datas = baseDao.findBySql(sql, null);
        Map<String, Integer> map = DataUtils.convertListToMap(datas,"time", "count");
        Map<String, Object> resMap = timeStatic(map);
        GeneralResult result = new GeneralResult();
        return result.ok(resMap);
    }

    @Override
    public GeneralResult articleDeafStatic() {
        String sql = "SELECT date(post_time) time, count(date(post_time)) count from tbl_article where \n" +
                "DATE_SUB(CURDATE(),INTERVAL 6 DAY) <= date(post_time)\n" +
                "and state = 2\n" +
                "GROUP BY time\n" +
                "ORDER BY time";
        List<Map<String, Object>> datas = baseDao.findBySql(sql, null);
        Map<String, Integer> map = DataUtils.convertListToMap(datas,"time", "count");
        Map<String, Object> resMap = timeStatic(map);
        GeneralResult result = new GeneralResult();
        return result.ok(resMap);
    }

    /**
     * 补齐时间空位
     * @param map
     * @return
     */
    private Map<String, Object> timeStatic(Map<String, Integer> map) {
        String[] xs = new String[7];//日期
        Integer[] xds = new Integer[7];//数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM-dd");
        Date cur = new Date();
        for (int i = 6; i >= 0; i--) {
            xs[i] = simpleDateFormat.format(cur.getTime() - i * (24 * 60 * 60 * 1000));
            if (map.containsKey(xs[i])) {
                xds[i] = map.get(xs[i]);
            } else {
                xds[i] = 0;
            }
            xs[i] = simpleDateFormat2.format(cur.getTime() - i * (24 * 60 * 60 * 1000));
        }
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("xs", xs);
        resMap.put("xds", xds);
        return resMap;
    }

    @Override
    public GeneralResult expensesData() {
        String sql = "SELECT time,if(SUM(income) is NULL,0,sum(income)) count from tbl_user_static where \n" +
                "DATE_SUB(CURDATE(),INTERVAL 6 DAY) <= date(time)\n" +
                "GROUP BY time\n" +
                "ORDER BY time";
        List<Map<String, Object>> datas = baseDao.findBySql(sql, null);
        Map<String, Integer> map = DataUtils.convertListToMapDecmail(datas,"time", "count");
        Map<String, Object> resMap = timeStatic(map);
        GeneralResult result = new GeneralResult();
        return result.ok(resMap);
    }
}
