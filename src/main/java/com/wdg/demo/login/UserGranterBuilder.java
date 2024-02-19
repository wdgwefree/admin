package com.wdg.demo.login;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 操作策略的上下文环境类
 * @author: wdg
 * @create: 2023-11-21 14:11
 */
@Component
public class UserGranterBuilder {

    private Map<String, UserGranter> granterPool = new ConcurrentHashMap<>();

    /**
     * 所有策略通过ioc自动注入放入到map中方便管理
     * 在实例化UserGranterBuilder时，spring ioc会自动会将
     * UserGranter所有的实例注入到它的有参构造中来时实现实例化
     * 将所有的策略串联起来的 关键所在
     */
    public UserGranterBuilder(Map<String, UserGranter> granterPool) {
        granterPool.forEach(this.granterPool::put);
    }


    /**
     * 对外提供获取具体策略
     */
    public UserGranter login(String type) {
        UserGranter userGranter = granterPool.get(type);
        if (userGranter == null) {
            System.out.println("未实现的认证方式");
        }
        return userGranter;
    }
}
