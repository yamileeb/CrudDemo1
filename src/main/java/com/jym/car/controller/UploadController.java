package com.jym.car.controller;

import com.jym.car.model.result.Result;
import com.jym.car.util.MinioUtilS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lb
 */
@RestController
@Slf4j
public class UploadController {

    @Resource
    private MinioUtilS minioUtilS;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result upload(@RequestBody MultipartFile[] file) {

        try {
            List<String> upload = minioUtilS.upload(file);
            return Result.ok(upload);
        } catch (Exception e) {
            log.error("上传错误,{}", e);
            return Result.error("error");
        }
    }


    /**
     * 下载文件
     *
     * @param filename
     */
    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename) {

        ResponseEntity<byte[]> download = minioUtilS.download(filename);
        return download;

    }
}
