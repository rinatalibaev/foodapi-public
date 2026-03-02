package ru.alibaev.foodapi.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "s3")
@Data
public class S3Properties {
    private String accessKey;
    private String secretKey;
    private String baseUrl;
    private String region;
    private String bucket;
}

