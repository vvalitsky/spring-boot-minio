package com.vvalitsky.storage.api

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.core.io.InputStreamResource
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile
import com.vvalitsky.storage.dto.FileUploadResponse

/**
 * Created by Vladislav Valitsky at 28.07.2020
 */

@Api(
    value = "StorageApi",
    description = "API for upload/download content"
)
interface StorageApi {

    @ApiOperation(
        httpMethod = "POST",
        value = "Upload file",
        response = FileUploadResponse::class
    )
    fun uploadFile(
        @ApiParam(required = true, value = "MultipartFile") file: MultipartFile,
        folder: String
    ): ResponseEntity<FileUploadResponse>

    @ApiOperation(
        httpMethod = "DELETE",
        value = "Delete file",
        response = Any::class
    )
    fun deleteFile(
        @ApiParam(required = true, value = "Folder") folder: String,
        @ApiParam(required = true, value = "File name") filename: String
    ): ResponseEntity<Any>

    @ApiOperation(
        httpMethod = "GET",
        value = "Download file",
        response = InputStreamResource::class
    )
    fun downloadFile(
        @ApiParam(required = true, value = "Folder") folder: String,
        @ApiParam(required = true, value = "File id") filename: String
    ): ResponseEntity<InputStreamResource>
}
