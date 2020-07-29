package com.vvalitsky.storage.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiParam

/**
 * Created by Vladislav Valitsky at 28.07.2020
 */

@ApiModel(
    value = "FileUploadResponse",
    description = "FileUploadResponse class, that stores file upload data"
)
data class FileUploadResponse(
    @ApiParam(value = "Uploaded file name")
    val filename: String,
    @ApiParam(value = "Uploaded file tag")
    val fileTag: String?,
    @ApiParam(value = "Uploaded file folder")
    val fileFolder: String,
    @ApiParam(value = "Uploaded file version")
    val versionId: String?
)
