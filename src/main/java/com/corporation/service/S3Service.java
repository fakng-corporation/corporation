package com.corporation.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * @author Bleschunov Dmitry
 */
@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    String bucketName;

    public String upload(MultipartFile file) {
        String objectName = createObjectName(file);
        File tempFile = multipartFileToTempFile(file);

        PutObjectRequest request = new PutObjectRequest(bucketName, objectName, tempFile)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        request.setMetadata(metadata);
        amazonS3.putObject(request);

        return amazonS3.getUrl(bucketName, objectName).toString();
    }

    private String getExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int lastDotIndex = fileName.lastIndexOf('.');
        return fileName.substring(lastDotIndex + 1);
    }

    private String createObjectName(MultipartFile file) {
        String extension = getExtension(file);
        return "avatar/" + bucketName + "-" + new Date().getTime() + "." + extension;
    }

    private File multipartFileToTempFile(MultipartFile file) {
        try {
            File tempFile;
            tempFile = File.createTempFile("multipartFile-", ".tmp");
            tempFile.deleteOnExit();
            file.transferTo(tempFile);
            return tempFile;
        } catch (Exception ignored) {}

        return null;
    }
}
