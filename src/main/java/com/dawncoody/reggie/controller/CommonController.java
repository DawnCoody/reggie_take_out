package com.dawncoody.reggie.controller;

import com.dawncoody.reggie.common.R;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID() + suffix;

        // 创建一个目录对象
        File dir = new File(basePath);
        // 判断当前目录是否存在
        if (!dir.exists()) {
            // 目录不存在，需要创建
            dir.mkdirs();
        }

        try {
            // 将临时文件转存到指定位置
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
             FileInputStream fileInputStream = new FileInputStream(basePath + name);
             // 输出流，通过输出流将文件写回浏览器，在浏览器显示图片
             ServletOutputStream outputStream = response.getOutputStream();
        ) {
            response.setContentType("image/type");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
