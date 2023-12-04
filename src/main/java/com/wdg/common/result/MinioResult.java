package com.wdg.common.result;

import lombok.Data;

/**
 * @description: minio的返回数据
 * @author: wdg
 * @create: 2023-12-01 18:03
 */
@Data
public class MinioResult {

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 保存url
     */
    private String saveUrl;

    /**
     * 访问url
     */
    private String accessUrl;

    /**
     * 上传/下载成功true，失败false
     */
    private Boolean flag;

    /**
     * 失败msg
     */
    private String msg;

    public MinioResult() {
    }

    /**
     * @param fileName
     * @param saveUrl
     * @param accessUrl
     */
    public MinioResult(String fileName, String saveUrl, String accessUrl) {
        this.fileName = fileName;
        this.saveUrl = saveUrl;
        this.accessUrl = accessUrl;
    }

    public MinioResult(Boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    /**
     * 失败
     *
     * @return
     */
    public static MinioResult error(String msg) {
        return new MinioResult(false, msg);
    }
}
