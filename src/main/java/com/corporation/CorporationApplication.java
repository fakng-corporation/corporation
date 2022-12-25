package com.corporation;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CorporationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorporationApplication.class, args);
    }

    @Bean
    public AmazonS3 amazonS3Client(
            @Value("${aws.credentials.aws_access_key_id}") String awsAccessKey,
            @Value("${aws.credentials.aws_secret_access_key}") String awsSecretKey,
            @Value("${aws.region}") String awsRegion
    ) {
        Regions clientRegion = Regions.valueOf(awsRegion);

        AWSCredentials credentials = new BasicAWSCredentials(
                awsAccessKey,
                awsSecretKey
        );

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(clientRegion)
                .build();
    }
}
