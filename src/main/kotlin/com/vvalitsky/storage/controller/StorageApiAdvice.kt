package com.vvalitsky.storage.controller

import java.util.Locale
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.multipart.MultipartException
import com.vvalitsky.storage.dto.ExceptionResponse

/**
 * Created by Vladislav Valitsky at 28.07.2020
 */

@ControllerAdvice
class StorageApiAdvice(
    private val messageSource: MessageSource,
    @Value("\${spring.servlet.multipart.max-file-size}")
    private val maxUploadFileSize: String
) {

    @ExceptionHandler(MultipartException::class)
    @ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
    fun handleMultipartException(ex: MultipartException): ResponseEntity<ExceptionResponse> {

        if (ex is MaxUploadSizeExceededException) {
            val uploadSizeExceededException = messageSource.getMessage(
                "file.upload.max-file-size.error", null, Locale("en")
            ).format(maxUploadFileSize)
            return ResponseEntity.badRequest().body(
                ExceptionResponse(
                    exceptionMessage = uploadSizeExceededException
                )
            )
        }

        return ResponseEntity.badRequest().body(
            ExceptionResponse(
                exceptionMessage = ex.message
            )
        )
    }
}
