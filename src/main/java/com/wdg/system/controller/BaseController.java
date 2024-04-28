package com.wdg.system.controller;

import com.github.pagehelper.PageInfo;
import com.wdg.common.dto.page.PageData;
import com.wdg.common.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @description: web层通用数据处理
 */
@Slf4j
public class BaseController {
    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageUtil.startPage();
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected PageData getPageData(List<?> list) {
        long total = new PageInfo(list).getTotal();
        return new PageData(list, total);
    }
}
