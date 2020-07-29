package com.vvalitsky.storage.config

import io.minio.MinioClient
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by Vladislav Valitsky at 28.07.2020
 */

@Configuration
@EnableConfigurationProperties(MinioProperties::class)
class MinioConfig(private val minioProperties: MinioProperties) {
    @Bean
    fun minioClient(): MinioClient {
        return MinioClient
            .builder()
            .credentials(
                minioProperties.accessKey,
                minioProperties.secretKey
            )
            .endpoint(minioProperties.url)
            .build()
    }
}
