package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.Province;

public interface ProvinceMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);
}