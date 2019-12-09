package com.pacman.bytes.demo.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
@Slf4j
public class AmazonSNSPublisherService implements PublisherService {

    private AmazonSNS amazonSNS;

    private boolean smsEnabled;

    @Autowired
    public AmazonSNSPublisherService(BasicAWSCredentials sessionCredentials,
                                     @Value("${sms.enabled}")boolean smsEnabled) {

        this.amazonSNS = AmazonSNSClientBuilder.standard().withRegion(Regions.EU_WEST_1).withCredentials(new AWSStaticCredentialsProvider(sessionCredentials)).build();
        this.smsEnabled = smsEnabled;

    }

    @Override
    public void publish(String message, String number) throws Exception {

        if (smsEnabled) {

            PublishRequest publishRequest = new PublishRequest()
                    .withMessage(message).withSubject("PacMan Password Reset").withPhoneNumber(number).withMessageAttributes(new HashMap<String, MessageAttributeValue>());
            PublishResult publishResult = this.amazonSNS.publish(publishRequest);

            log.debug("MessageId - " + publishResult.getMessageId());
        } else {
            log.debug(String.format("SMS Disabled : Message was [%s]", message));
        }

    }


}
