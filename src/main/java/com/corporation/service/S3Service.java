package com.corporation.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.corporation.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Bleschunov Dmitry
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public String upload(MultipartFile file) {
        try {
            String objectName = createObjectName(file);
            File tempFile = createTempFileFrom(file);

            PutObjectRequest request = new PutObjectRequest(bucketName, objectName, tempFile)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            request.setMetadata(metadata);
            amazonS3.putObject(request);

            tempFile.delete();

            return amazonS3.getUrl(bucketName, objectName).toString();
        } catch (IOException | IllegalArgumentException e) {
            String message = "Server exception";
            log.error(message, e);
            throw new BusinessException(message, e);
        } catch (AmazonServiceException e) {
            String message = "Amazon exception";
            log.error(message, e);
            throw new BusinessException(message, e);
        }
    }

    private String getExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        if (fileName == null)
            throw new IllegalArgumentException();

        int lastDotIndex = fileName.lastIndexOf('.');
        return fileName.substring(lastDotIndex + 1);
    }

    private String createObjectName(MultipartFile file) {
        String extension = getExtension(file);
        return "avatar/" + bucketName + "-" + LocalDateTime.now() + "." + extension;
    }

    private File createTempFileFrom(MultipartFile file) throws IOException {
        File tempFile;
        tempFile = File.createTempFile("multipartFile-", ".tmp");
        file.transferTo(tempFile);
        return tempFile;
    }
}
