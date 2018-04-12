package com.cqust.blog.service_api.web;

import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;

/**
 * Created by Administrator on 2018/4/11.
 */
public interface CollectionArticleService {
    GeneralResult addCollection(User sessionUser, Integer aid);
}
