package com.wdg.common.utils;


import com.wdg.common.dto.result.MinioResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件服务
 */
@Service
public class FileService {
    @Resource
    private MinioUtil minioUtil;

    /**
     * 上传文件
     *
     * @param file
     * @param path 不带文件名的路径
     * @return
     */
    public MinioResult uploadFile(MultipartFile file, String path) {
        String fullPath = minioUtil.getFullPath(file, path);
        MinioResult minioResult = minioUtil.uploadFile(file, fullPath);
        return minioResult;
    }
}
