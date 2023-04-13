package com.dawncoody.reggie.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-04-13 12:04
 */
public interface OssService {
    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);

    /**
     * 文件下载
     *
     * @param fileName
     * @return
     */
    InputStream downloadFile(String fileName);

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    String deleteFile(String fileName);
}
