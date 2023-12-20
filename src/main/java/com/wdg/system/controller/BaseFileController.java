package com.wdg.system.controller;

import com.wdg.system.service.BaseFileService;
import com.wdg.common.constant.FileConstants;
import com.wdg.common.result.ApiResult;
import com.wdg.common.result.MinioResult;
import com.wdg.common.utils.MinioUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wdg
 * @create: 2023-12-01 18:27
 */
@Api(tags = "文件")
@RequestMapping("/base/file")
@RestController
public class BaseFileController {

    @Resource
    private BaseFileService baseFileService;

    @Resource
    private MinioUtil minioUtil;

    @ApiOperation(value = "上传文件")
    @PostMapping("/uploadFile")
    public ApiResult uploadFile(List<MultipartFile> files) {
        ArrayList<MinioResult> minioResults = new ArrayList<>();
        for (MultipartFile file : files) {
            MinioResult minioResult = baseFileService.uploadFile(file, FileConstants.USER_IMAGES);
            minioResults.add(minioResult);
        }
        return ApiResult.success(minioResults);
    }

    @ApiOperation(value = "下载文件")
    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response,String fullPath, String fileName){
        try {
            minioUtil.downLoadFile(response, fullPath, fileName);
        } catch (Exception e) {
            ApiResult.exception("文件下载失败");
        }
    }

}
