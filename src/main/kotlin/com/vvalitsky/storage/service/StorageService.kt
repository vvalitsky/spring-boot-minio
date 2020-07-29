package com.vvalitsky.storage.service

import org.springframework.core.io.InputStreamResource
import org.springframework.web.multipart.MultipartFile
import com.vvalitsky.storage.dto.FileUploadResponse

/**
 * Created by Vladislav Valitsky at 28.07.2020
 */

interface StorageService {
    fun uploadFile(file: MultipartFile, folder: String): FileUploadResponse
    fun deleteFile(folder: String, filename: String)
    fun downloadFile(folder: String, filename: String): InputStreamResource
}
