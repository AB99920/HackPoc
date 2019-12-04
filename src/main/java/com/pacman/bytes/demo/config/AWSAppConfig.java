package com.pacman.bytes.demo.config;

import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AWSAppConfig {

    private static final Integer TEMPORARY_CREDENTIALS_DURATION_DEFAULT = 7200;


    @Bean(name = "sessionCredentials")
    public BasicAWSCredentials sessionCredentials(@Value("aws.access.key") String accessKey,
                                                  @Value("aws.access.secret") String password) {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, password);

        return awsCreds;


    }
}
