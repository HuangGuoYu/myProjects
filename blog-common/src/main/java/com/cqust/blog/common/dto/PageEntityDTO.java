package com.cqust.blog.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */
@Getter
@Setter
public class PageEntityDTO<T> {


    private List<T> datas;

    private double pageCount;

    private Integer perCount = 10;

    private Integer curPage = 1;

    public PageEntityDTO(List<T> datas) {
        this.datas = datas;
        this.pageCount = Math.ceil(datas.size() / perCount * 1.0);
    }
}
