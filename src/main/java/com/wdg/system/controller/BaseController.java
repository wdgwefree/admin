package com.wdg.system.controller;

import com.wdg.common.result.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: web层通用数据处理
 * @author: wdg
 * @create: 2023-12-18 14:01
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
}
