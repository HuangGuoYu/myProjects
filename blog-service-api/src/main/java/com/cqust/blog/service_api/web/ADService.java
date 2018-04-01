package com.cqust.blog.service_api.web;

import com.cqust.blog.common.resp.GeneralResult;

/**
 * Created by Administrator on 2018/3/25.
 */
public interface ADService {
    /**
     * 广告列表
     * @return 数据集
     */
    GeneralResult<?> adList(Integer num);

}
