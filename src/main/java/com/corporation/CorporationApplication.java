package com.corporation;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CorporationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorporationApplication.class, args);
    }

    @Value("${aws.credentials.aws_access_key_id}")
    private String awsAccessKey;

    @Value("${aws.credentials.aws_secret_access_key}")
    private String awsSecretKey;

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    public AmazonSimpleEmailService amazonSes() {
        return AmazonSimpleEmailServiceClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
                .withRegion(clientRegion()).build();

    }

    @Bean
    public AmazonS3 amazonS3Client(
    ) {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
                .withRegion(clientRegion())
                .build();
    }

    @Bean
    public Regions clientRegion() {
        return Regions.valueOf(awsRegion);
    }

    @Bean
    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials(
                awsAccessKey,
                awsSecretKey
        );
    }
}
