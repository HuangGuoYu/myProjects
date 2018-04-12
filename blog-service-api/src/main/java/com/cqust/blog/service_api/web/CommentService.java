package com.cqust.blog.service_api.web;

import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;

/**
 * Created by Administrator on 2018/4/11.
 */
public interface CommentService {
    /**
     * 添加评论
     * @param content 内容
     * @param aid id
     * @param sessionUser 操作用户
     * @return 处理结果
     */
    GeneralResult addComemnt(String content, Integer aid, User sessionUser);

    /**
     * 根据文章ID查询评论
     * @param aid
     * @return
     */
    GeneralResult queryDataByAid(String aid);
}
