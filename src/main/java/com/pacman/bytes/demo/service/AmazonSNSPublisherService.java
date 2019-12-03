package com.pacman.bytes.demo.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AmazonSNSPublisherService implements PublisherService {

    private AmazonSNS amazonSNS;
    private String snsTopicDemoARN;

    @Autowired
    public AmazonSNSPublisherService(BasicAWSCredentials sessionCredentials, String snsTopicDemoARN) {

        this.amazonSNS = AmazonSNSClientBuilder.standard().withRegion(Regions.EU_WEST_1).withCredentials(new AWSStaticCredentialsProvider(sessionCredentials)).build();
        this.snsTopicDemoARN = snsTopicDemoARN;


    }

    @Override
    public void publish(String message, String number) throws Exception {



        String snsTopic = getTopicARN(snsTopicDemoARN);
        PublishRequest publishRequest = new PublishRequest()
             //   .withTopicArn(snsTopic)
                .withMessage(message).withPhoneNumber(number).withMessageAttributes(new HashMap<String, MessageAttributeValue>());
        PublishResult publishResult = this.amazonSNS.publish(publishRequest);

        System.out.println("MessageId - " + publishResult.getMessageId());

    }

    private String getTopicARN(String topic) throws Exception {
        switch (topic) {
            case AWS_SNS_DEMO:
                return this.snsTopicDemoARN;
            default:
                throw new RuntimeException("No matching topic supported!");
        }
    }
}
