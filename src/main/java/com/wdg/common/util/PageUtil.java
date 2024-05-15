package com.wdg.common.util;

import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wdg.common.exception.BusinessException;
import com.wdg.common.enums.ResultCode;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页工具
 */
public class PageUtil {

    /**
     * 设置请求分页数据
     */
    public static void startPage() {
        HttpServletRequest request = MyServletUtil.getRequest();
        try {
            String body = ServletUtil.getBody(request);
            if (StringUtils.isNotEmpty(body)) {
                JSONObject jsonObject = JSONObject.parseObject(body);
                if (jsonObject.containsKey("pageNum") && jsonObject.containsKey("pageSize")) {
                    Integer pageNum = jsonObject.getInteger("pageNum");
                    Integer pageSize = jsonObject.getInteger("pageSize");
                    PageHelper.startPage(pageNum, pageSize);
                    return;
                }
            }
        } catch (Exception e) {
            throw new BusinessException(ResultCode.PARAM_ERROR.getCode(), "分页参数异常");
        }
    }
}
