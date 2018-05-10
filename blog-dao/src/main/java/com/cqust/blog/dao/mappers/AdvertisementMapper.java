package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.Advertisement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    Advertisement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);

    List<Advertisement> selectByOrd(@Param("num") Integer num);
}