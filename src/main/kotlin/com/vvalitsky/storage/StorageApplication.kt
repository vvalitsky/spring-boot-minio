package com.vvalitsky.storage

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by Vladislav Valitsky at 28.07.2020
 */

@SpringBootApplication
class StorageApplication

fun main(args: Array<String>) {
    SpringApplication.run(StorageApplication::class.java, *args)
}
