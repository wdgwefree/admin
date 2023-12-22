package com.wdg.system.controller;

import com.github.pagehelper.PageInfo;
import com.wdg.common.dto.PageData;
import com.wdg.common.result.ApiResult;
import com.wdg.common.utils.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @description: web层通用数据处理
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected ApiResult toApiResult(boolean result) {
        return result ? ApiResult.success() : ApiResult.error();
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected ApiResult toApiResult(int rows) {
        return rows > 0 ? ApiResult.success() : ApiResult.error();
    }

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
