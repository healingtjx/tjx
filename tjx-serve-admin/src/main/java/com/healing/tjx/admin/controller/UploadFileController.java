package com.healing.tjx.admin.controller;

import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.exception.Asserts;
import com.healing.tjx.common.oss.OSSFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author tjx
 * @Description 文件上传
 * @Date 2021/5/23 10:47 下午
 */
@Slf4j
@RestController
@RequestMapping("/upload/file")
@Api(tags = "UploadFileController", value = "文件上传")
public class UploadFileController {

    @ApiOperation(value = "上传图片")
    @PostMapping("/img")
    public CommonResult list(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            Asserts.fail("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);
        //返回url
        return CommonResult.success(url);
    }
}
