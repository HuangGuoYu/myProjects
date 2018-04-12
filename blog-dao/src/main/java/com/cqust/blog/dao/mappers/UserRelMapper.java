package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.UserRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRel record);

    int insertSelective(UserRel record);

    UserRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRel record);

    int updateByPrimaryKey(UserRel record);

    UserRel queryByUidToUid(@Param("uid") Integer uid,@Param("toid") Integer toUser);
}