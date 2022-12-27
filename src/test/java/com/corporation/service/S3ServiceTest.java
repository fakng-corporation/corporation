package com.corporation.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.corporation.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class S3ServiceTest {

    @Mock
    private AmazonS3 amazonS3;

    @InjectMocks
    private S3Service s3Service;

    @Test
    public void shouldUploadUserAvatarToS3() throws MalformedURLException {
        MultipartFile file = new MockMultipartFile("avatar.png", "avatar.png", "image/png", new byte[]{1, 2, 3});
        Mockito.when(amazonS3.getUrl(Mockito.any(), Mockito.any()))
                .thenReturn(new URL("https://s3.com"));

        String url = s3Service.upload(file);

        Assertions.assertNotNull(url);
        Mockito.verify(amazonS3).putObject(Mockito.any(PutObjectRequest.class));
    }

    @Test
    public void shouldThrowBusinessException() {
        MultipartFile file = new MockMultipartFile("avatar.png", "avatar.png", "image/png", new byte[]{1, 2, 3});
        Mockito.when(amazonS3.putObject(Mockito.any()))
                .thenThrow(AmazonServiceException.class);

        Assertions.assertThrows(BusinessException.class, () -> s3Service.upload(file));
    }
}
