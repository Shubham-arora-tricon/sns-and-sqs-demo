package com.triconinfotech.aws.sns.web;

import com.triconinfotech.aws.sqs.entity.Employee;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationSubject;
import org.springframework.cloud.aws.messaging.endpoint.NotificationStatus;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationMessageMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationSubscriptionMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationUnsubscribeConfirmationMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-topic")
public class SNSController2 {
    @NotificationSubscriptionMapping
    public void confirmUnsubscribeMessage(NotificationStatus notificationStatus) {
        notificationStatus.confirmSubscription();
    }

    @NotificationMessageMapping
    public void receiveNotification(@NotificationMessage Employee message,
                                    @NotificationSubject String subject) {
        // handle message
    }

    @NotificationUnsubscribeConfirmationMapping
    public void confirmSubscriptionMessage(NotificationStatus notificationStatus) {
        notificationStatus.confirmSubscription();
    }
}
