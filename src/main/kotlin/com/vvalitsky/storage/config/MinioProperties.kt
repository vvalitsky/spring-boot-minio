package com.vvalitsky.storage.config

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Created by Vladislav Valitsky at 28.07.2020
 */

@ConfigurationProperties(value = "storage.minio")
class MinioProperties {
    var accessKey: String = ""
    var secretKey: String = ""
    var url: String = ""
}
