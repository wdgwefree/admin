package com.wdg.common.dto.result;

import lombok.Data;

/**
 * minio返回数据
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
