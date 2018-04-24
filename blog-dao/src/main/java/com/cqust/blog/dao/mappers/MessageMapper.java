package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    Message findIsExists(@Param("fromId") Integer fromId, @Param("toId") Integer toId);

    List<Map<String,Object>> findFriendList(@Param("uid") Integer id);

    List<Message> findIsExistsUnReadMessage(@Param("uid") Integer id);
}