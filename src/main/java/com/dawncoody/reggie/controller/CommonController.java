package com.dawncoody.reggie.controller;

import com.aliyun.oss.OSS;
import com.dawncoody.reggie.common.R;
import com.dawncoody.reggie.service.impl.OssServiceImpl;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @description: 文件的上传和下载
 * @author: dawncoody
 * @create: 2023-03-23 19:18
 */

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Autowired
    OssServiceImpl ossService;
    @Value("${reggie.path}")
    private String basePath;
    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(@RequestPart("file") MultipartFile file) {
        log.info(file.toString());
        String fileName = ossService.uploadFile(file);
        return R.success(fileName);
    }

    /**
     * 文件下载
     *
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {

        try (// 输入流，通过输入流读取文件内容
             // FileInputStream fileInputStream = new FileInputStream(ResourceUtils.getURL("classpath:").getPath() + basePath + name);
             InputStream inputStream = ossService.downloadFile(name);
             // 输出流，通过输出流将文件写回浏览器，在浏览器显示图片
             ServletOutputStream outputStream = response.getOutputStream();
        ) {

            log.info("上下文路径为{}", ResourceUtils.getURL("classpath:").getPath());
            response.setContentType("image/type");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
