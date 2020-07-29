package com.vvalitsky.storage.service

import io.minio.BucketExistsArgs
import io.minio.GetObjectArgs
import io.minio.MakeBucketArgs
import io.minio.MinioClient
import io.minio.PutObjectArgs
import io.minio.RemoveObjectArgs
import java.lang.Exception
import java.time.Instant
import org.apache.tika.Tika
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.InputStreamResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import com.vvalitsky.storage.dto.FileUploadResponse

/**
 * Created by Vladislav Valitsky at 28.07.2020
 */

@Service
class StorageServiceImpl(
    private val minioClient: MinioClient,
    private val tika: Tika
) : StorageService {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun uploadFile(file: MultipartFile, folder: String): FileUploadResponse {
        val bucket = BucketExistsArgs.builder().bucket(folder).build()

        if (!minioClient.bucketExists(bucket)) {
            log.info("Create of Minio bucket $folder")
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(folder).build())
            log.info("Bucket $folder was successfully created")
        }

        val filename = "${Instant.now().epochSecond}_${file.originalFilename}"

        val putObject = PutObjectArgs
            .builder()
            .contentType(detectMimeType(file))
            .bucket(folder)
            .`object`(filename)
            .stream(file.inputStream, file.size, -1)
            .build()

        val response = minioClient.putObject(putObject)
        log.info("$filename was pushed into file storage")
        return FileUploadResponse(
            filename = filename,
            fileTag = response.etag(),
            fileFolder = folder,
            versionId = response.versionId()
        )
    }

    override fun deleteFile(folder: String, filename: String) {
        log.info("Start deleting of $filename in $folder")
        minioClient.removeObject(
            RemoveObjectArgs.builder()
                .bucket(folder)
                .`object`(filename)
                .build()
        )
    }

    override fun downloadFile(folder: String, filename: String): InputStreamResource {
        return InputStreamResource(
            minioClient.getObject(
                GetObjectArgs
                    .builder()
                    .`object`(filename)
                    .bucket(folder)
                    .build()
            )
        )
    }

    private fun detectMimeType(file: MultipartFile): String {
        try {
            return tika.detect(file.inputStream)
        } catch (ex: Exception) {
            log.warn("File mime type cannot be detected ${file.originalFilename}")
        }
        return ""
    }
}
