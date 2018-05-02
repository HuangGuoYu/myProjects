package com.cqust.blog.manager.controller.dao;

import com.cqust.blog.common.entity.Article;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/29.
 */
public interface BaseDao {

    <T> List<T> findAllEntityByClass(Class<T> entity);

    List<Map<String,Object>> findBySql(String sql, Map<String, Object> params);

    /**
     * 根据类型和id查询实体
     * @param id 实体id
     * @param tClass 实体类型
     * @param <T> 实体泛型
     * @return 实体
     */
    <T> T findEntityById(Serializable id, Class<T> tClass);

    /**
     * 保存实体
     * @param entity
     * @param <T>
     */
    <T> void execEntitySave(T entity);

    /**
     * 更新实体
     * @param entity
     * @param <T>
     */
    <T> void execEntityUpdate(T entity);

    /**
     * 通过hql查找实体
     * @param hql hql
     * @param params
     * @param <T>
     * @return
     */
    <T> List<T> findEntityByHql(String hql, Map<String, Object> params);

    /**
     * 执行sql
     * @param sql sql
     * @param params 参数
     * @return 处理结果
     */
    int execUpdate(String sql, Map<String, Object> params);

}
