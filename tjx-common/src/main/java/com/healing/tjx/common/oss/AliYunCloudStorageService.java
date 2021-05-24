package com.healing.tjx.common.oss;

import com.aliyun.oss.OSSClient;
import com.healing.tjx.common.exception.Asserts;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author tjx
 * @Description 阿里云oss配置
 * @Date 2021/5/23 10:19 下午
 */
public class AliYunCloudStorageService extends CloudStorageService {

    private OSSClient client;

    public AliYunCloudStorageService(CloudStorageConfig config) {
        this.config = config;
        //初始化
        init();
    }

    private void init() {
        client = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
                config.getAliyunAccessKeySecret());
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            client.putObject(config.getAliyunBucketName(), path, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            Asserts.fail("上传文件失败，请检查配置信息");
        }
        return config.getAliyunDomain() + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getAliyunPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getAliyunPrefix(), suffix));
    }
}
