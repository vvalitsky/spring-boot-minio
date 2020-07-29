package com.vvalitsky.storage.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiParam

/**
 * Created by Vladislav Valitsky at 28.07.2020
 */

@ApiModel(
    value = "ExceptionResponse",
    description = "ExceptionResponse class, that stores exception data"
)
data class ExceptionResponse(
    @ApiParam(value = "Exception message")
    val exceptionMessage: String?
)
