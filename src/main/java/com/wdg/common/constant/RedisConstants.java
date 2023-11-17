package com.wdg.common.constant;

/**
 * redis 常量
 */
public class RedisConstants {

    /**
     * 证书订单审核锁定key前缀
     */
    public static final String ORDER_EXAMINE_LOCK="order_examine_lock:";

    /**
     * 证书下载锁定key前缀
     */
    public static final String CERT_DOWNLOAD_LOCK="cert_download_lock:";
}
