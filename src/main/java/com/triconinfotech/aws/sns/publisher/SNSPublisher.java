package com.triconinfotech.aws.sns.publisher;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.triconinfotech.aws.sqs.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class SNSPublisher {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NotificationMessagingTemplate notificationMessagingTemplate;

    public void send(String subject, Employee message) {
        logger.info("Subject with value {} and message with value {} to be send", subject, message);
        this.notificationMessagingTemplate.sendNotification("test-topic", message, subject);
        logger.info("message sent succussfully with value {}", message);
    }
}
