package com.dawncoody.reggie.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import com.dawncoody.reggie.config.OssConfig;
import com.dawncoody.reggie.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-04-13 12:06
 */
@Service
public class OssServiceImpl implements OssService {
    @Autowired
    private OssConfig ossConfig;
    @Autowired
    private OSS ossClient;
    @Value("${reggie.path}")
    private String basePath;

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        // 获取基本信息
        String endpoint = ossConfig.getEndpoint();
        String bucketName = ossConfig.getBucketName();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();


        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID() + suffix;

        /*// 创建一个目录对象
        File dir = new File(basePath);
        // 判断当前目录是否存在
        if (!dir.exists()) {
            // 目录不存在，需要创建
            dir.mkdirs();
        }*/
        // 文件上传路径
        String uploadURl = basePath + fileName;
        InputStream inputStream = null;

        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 上传到阿里云
        ossClient.putObject(bucketName, uploadURl, inputStream);
        return fileName;
    }

    @Override
    public InputStream downloadFile(String fileName) {
        OSSObject ossObject = ossClient.getObject(ossConfig.getBucketName(), basePath + fileName);
        return ossObject.getObjectContent();
    }

    @Override
    public String deleteFile(String fileName) {
        ossClient.deleteObject(ossConfig.getBucketName(), basePath + fileName);
        return null;
    }
}
