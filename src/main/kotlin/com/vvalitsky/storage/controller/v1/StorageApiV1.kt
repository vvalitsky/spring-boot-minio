package com.vvalitsky.storage.controller.v1

import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import com.vvalitsky.storage.api.StorageApi
import com.vvalitsky.storage.dto.FileUploadResponse
import com.vvalitsky.storage.service.StorageService

/**
 * Created by Vladislav Valitsky at 28.07.2020
 */

@RestController
@RequestMapping("/api/v1/storage")
class StorageApiV1(
    private val storageService: StorageService
) : StorageApi {

    @RequestMapping(
        value = [ "/upload/{folder}" ],
        produces = [ MediaType.APPLICATION_JSON_VALUE ],
        method = [ RequestMethod.POST ]
    )
    override fun uploadFile(
        @RequestParam("file") file: MultipartFile,
        @PathVariable("folder") folder: String
    ): ResponseEntity<FileUploadResponse> {
        return ResponseEntity.ok(storageService.uploadFile(file, folder))
    }

    @RequestMapping(
        value = [ "/delete/{folder}/{filename}" ],
        produces = [ MediaType.APPLICATION_JSON_VALUE ],
        method = [ RequestMethod.DELETE ]
    )
    override fun deleteFile(
        @PathVariable("folder") folder: String,
        @PathVariable("filename") filename: String
    ): ResponseEntity<Any> {
        return ResponseEntity.ok(storageService.deleteFile(folder, filename))
    }

    @RequestMapping(
        value = [ "/resource/{folder}/{filename}" ],
        method = [ RequestMethod.GET ]
    )
    override fun downloadFile(
        @PathVariable("folder") folder: String,
        @PathVariable("filename") filename: String
    ): ResponseEntity<InputStreamResource> {

        val headers = HttpHeaders().apply {
            add("Content-Disposition", "attachment; filename=$filename")
        }
        return ResponseEntity
            .ok()
            .headers(headers)
            .body(
                storageService.downloadFile(folder, filename)
            )
    }
}
