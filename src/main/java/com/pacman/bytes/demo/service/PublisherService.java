package com.pacman.bytes.demo.service;


public interface PublisherService {

    //
    // Publish Message API
    //
    void publish(String message, String number)throws Exception;
}
