package com.corporation.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class S3ServiceTest {

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @InjectMocks
    private S3Service s3Service;

    @Test
    public void shouldReturnExtension() throws
            NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException
    {
        MultipartFile file = new MockMultipartFile("avatar.png", "avatar.png", "image/png", new byte[]{1, 2, 3});

        Method getExtension = s3Service.getClass().getDeclaredMethod("getExtension", MultipartFile.class);
        getExtension.setAccessible(true);

        Assertions.assertEquals("png", getExtension.invoke(s3Service, file));
    }

    @Test
    public void shouldReturnCorrectObjectName() throws
            NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException
    {
        MultipartFile file = new MockMultipartFile("avatar.png", "avatar.png", "image/png", new byte[]{1, 2, 3});
        // "avatar/" + bucketName + "-" + LocalDateTime.now() + "." + extension;
        // avatar/fakngcorporation-2022-12-25T10:34:05.359840.jpg
        Pattern pattern = Pattern.compile(
                "avatar\\/" + bucketName +"-\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{2}.\\d+.png"
        );
        Method createObjectName = s3Service.getClass().getDeclaredMethod("createObjectName", MultipartFile.class);
        createObjectName.setAccessible(true);

        Object object = createObjectName.invoke(s3Service, file);
        Assertions.assertNotNull(object);
        String objectName = (String) object;
        Matcher matcher = pattern.matcher(objectName);

        Assertions.assertTrue(matcher.matches());
    }

    @Test
    public void shouldReturnTempFile() throws
            NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException
    {
        MultipartFile file = new MockMultipartFile("avatar.png", "avatar.png", "image/png", new byte[]{1, 2, 3});
        Method createTempFileFrom = s3Service.getClass().getDeclaredMethod("createTempFileFrom", MultipartFile.class);
        createTempFileFrom.setAccessible(true);

        Object obj = createTempFileFrom.invoke(s3Service, file);

        Assertions.assertNotNull(obj);
    }
}
