package com.wdg.common.utils;

import cn.hutool.core.util.IdUtil;
import com.wdg.common.dto.result.MinioResult;
import io.minio.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @description: MinIo工具类
 * @author: wdg
 * @create: 2023-12-01 16:19
 */
@Component
public class MinioUtil {

    @Resource
    private MinioClient client;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String endpoint;

    /**
     * 判断存储桶是否存在
     *
     * @param bucketName 桶名称
     * @return
     */
    public boolean bucketExists(String bucketName) {
        try {
            return client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 创建存储桶
     *
     * @param bucketName 桶名称
     */
    public void createBucket(String bucketName) {
        try {
            boolean isExist = bucketExists(bucketName);
            if (!isExist) {
                client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除一个文件
     *
     * @param objectName 对象名称
     */
    @SneakyThrows
    public boolean removeObject(String objectName) {
        if (!bucketExists(bucketName)) {
            return false;
        }
        client.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build());
        return true;
    }

    /**
     * 上传文件到指定目录
     *
     * @param file     MultipartFile
     * @param fullPath 文件路径，包含文件名
     * @return
     */
    public MinioResult uploadFile(MultipartFile file, String fullPath) {
        try {
            InputStream inputStream = file.getInputStream();
            ObjectWriteResponse objectWriteResponse = client.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fullPath)
                    .stream(inputStream, inputStream.available(), -1)
                    .contentType(file.getContentType())
                    .build());
            inputStream.close();
        } catch (Exception e) {
            return MinioResult.error(e.getMessage());
        }
        // 返回生成文件名、保存路径、访问路径
        String accessUrl = endpoint + "/" + bucketName + fullPath;
        return new MinioResult(file.getOriginalFilename(), fullPath, accessUrl);
    }


    /**
     * 下载文件
     * @param response
     * @param fullPath
     * @param fileName
     * @throws Exception
     */
    public void downLoadFile(HttpServletResponse response, String fullPath, String fileName) throws Exception {

        InputStream inputStream = client.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(fullPath).build());

        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/x-msdownload");
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");

        byte buf[] = new byte[1024];
        int length = 0;
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        while ((length = inputStream.read(buf)) > 0) {
            outputStream.write(buf, 0, length);
        }
        outputStream.close();
    }

    /**
     * 获取文件fullPath
     */
    public String getFullPath(MultipartFile file, String path) {
        String uuid = IdUtil.fastSimpleUUID();
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String suffix = fileName.substring(index);
        String fullPath = path + uuid + suffix;
        return fullPath;
    }


}
