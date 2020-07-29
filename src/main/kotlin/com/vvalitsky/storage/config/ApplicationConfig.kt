package com.vvalitsky.storage.config

import org.apache.tika.Tika
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.scheduling.annotation.EnableAsync

/**
 * Created by Vladislav Valitsky at 28.07.2020
 */

@Configuration
@EnableAsync
class ApplicationConfig {
    @Bean
    fun tika(): Tika {
        return Tika()
    }

    @Bean
    fun messageSource(): MessageSource = ReloadableResourceBundleMessageSource().apply {
        setBasename("classpath:messages")
        setDefaultEncoding("UTF-8")
    }
}
