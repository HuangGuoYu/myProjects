package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.Withdraw;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WithdrawMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Withdraw record);

    int insertSelective(Withdraw record);

    Withdraw selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Withdraw record);

    int updateByPrimaryKey(Withdraw record);

    List<Withdraw> findWithdrawRecord(@Param("uid") Integer id);
}