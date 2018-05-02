package com.cqust.blog.manager.controller.service;

import com.cqust.blog.common.entity.Advertisement;
import com.cqust.blog.common.resp.GeneralResult;

/**
 * Created by Administrator on 2018/5/2.
 */
public interface StaticFileService {
    /**
     * 添加广告
     * @param advertisement 广告
     * @return
     */
    GeneralResult execSaveAd(Advertisement advertisement);

    /**
     * 所有广告
     * @return
     */
    GeneralResult findAdList();

    /**
     * 下架广告
     * @param id
     * @return
     */
    GeneralResult execUpdateState(Integer id);

    GeneralResult execadApproval(Integer id);
}
