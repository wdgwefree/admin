package com.wdg.system.service;

import com.wdg.common.dto.result.MinioResult;
import com.wdg.common.utils.MinioUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @description:
 * @author: wdg
 * @create: 2023-12-01 18:33
 */
@Service
public class BaseFileService {

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
