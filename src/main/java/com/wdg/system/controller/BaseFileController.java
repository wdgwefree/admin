package com.wdg.system.controller;

import com.wdg.system.service.BaseService;
import com.wdg.common.constant.FileConstants;
import com.wdg.common.dto.result.ApiResult;
import com.wdg.common.dto.result.MinioResult;
import com.wdg.common.utils.MinioUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/system/file")
@RestController
public class BaseFileController {

    @Resource
    private BaseService baseService;

    @Resource
    private MinioUtil minioUtil;

    /**
     * 上传文件列表
     */
    @PostMapping("/uploadFile")
    public ApiResult uploadFile(List<MultipartFile> files) {
        ArrayList<MinioResult> minioResults = new ArrayList<>();
        for (MultipartFile file : files) {
            MinioResult minioResult = baseService.uploadFile(file, FileConstants.USER_IMAGES);
            minioResults.add(minioResult);
        }
        return ApiResult.success(minioResults);
    }

    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response, String fullPath, String fileName) {
        try {
            minioUtil.downLoadFile(response, fullPath, fileName);
        } catch (Exception e) {
            ApiResult.exception("文件下载失败");
        }
    }

}
