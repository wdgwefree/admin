package com.wdg.system.controller;

import com.github.pagehelper.PageInfo;
import com.wdg.common.dto.page.PageData;
import com.wdg.common.util.PageUtil;
import com.wdg.system.dto.LoginInfoDTO;
import com.wdg.system.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * @description: web层通用数据处理
 */
@Slf4j
public class BaseController {

    @Autowired
    private TokenUtil tokenUtil;

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

    /**
     * 获取用户缓存信息(开放接口无法使用！)
     */
    public LoginInfoDTO getLoginInfoDTO() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LoginInfoDTO loginInfo = tokenUtil.getLoginInfoDTO();
        stopWatch.stop();
        System.out.println("获取用户缓存信息耗时：" + stopWatch.getTotalTimeMillis() + "毫秒");
        return loginInfo;
    }
}
