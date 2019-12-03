package com.pacman.bytes.demo.service;


public interface PublisherService {
    //
    // Name of the topic
    //
    public static final String AWS_SNS_DEMO = "arn:aws:sns:eu-west-1:938086318949:hackdemo";

    //
    // Publish Message API
    //
    void publish(String message, String number)throws Exception;
}
