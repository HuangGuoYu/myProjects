package com.cqust.blog.manager.controller.dao.impl;

import com.cqust.blog.common.entity.Article;
import com.cqust.blog.manager.controller.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/29.
 */
@Repository
public class BaseDaoImpl implements BaseDao {
    @Autowired
    SessionFactory sessionFactory;

    /**
     * 根据class查询实体
     * @param entity 实体class对象
     * @param <T> 返回值类型
     * @return 数据集
     */
    @Override
    public <T> List<T> findAllEntityByClass(Class<T> entity) {
        Session session = getSession();
        Query query = session.createQuery("from " + entity.getSimpleName());
        List<T> list = query.list();
        closeSession(session);
        return list;
    }

    @Override
    public List<Map<String, Object>> findBySql(String sql, Map<String, Object> params) {
        Session session = getSession();
        Query query = session.createSQLQuery(sql);
        addParamter(query, params);
        //设定结果结果集中的每个对象为Map类型
        query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        closeSession(session);
        return list;
    }


    /**
     * 给query对象添加参数
     * @param query 查询对象
     * @param params 参数
     */
    private void addParamter(Query query, Map<String, Object> params) {
        if (params == null) {
            return;
        }
        for (Map.Entry<String, Object> item : params.entrySet()) {
            query.setParameter(item.getKey(), item.getValue());
        }
    }

    @Override
    public <T> T findEntityById(Serializable id, Class<T> tClass) {
        Session session = getSession();
        Object o = session.get(tClass, id);
        closeSession(session);
        return (T) o;
    }

    @Override
    public <T> void execEntityUpdate(T entity) {
        Session session = getSession();
        session.update(entity);
        session.flush();
        closeSession(session);
    }

    @Override
    public <T> List<T> findEntityByHql(String hql, Map<String, Object> params) {
        Session session = getSession();
        Query query = session.createQuery(hql);
        addParamter(query, params);
        List<T> list = query.list();
        closeSession(session);
        return list;
    }

    @Override
    public int execUpdate(String sql, Map<String, Object> params) {
        Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        addParamter(sqlQuery, params);
        int i = sqlQuery.executeUpdate();
        session.flush();
        closeSession(session);
        return i;
    }

    @Override
    public <T> void execEntitySave(T entity) {
        Session session = getSession();
        session.save(entity);
        session.flush();
        closeSession(session);
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }

    private void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
    }
}
