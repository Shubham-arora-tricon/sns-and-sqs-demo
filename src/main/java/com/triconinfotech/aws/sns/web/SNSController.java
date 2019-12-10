package com.triconinfotech.aws.sns.web;

import com.triconinfotech.aws.sns.publisher.SNSPublisher;
import com.triconinfotech.aws.sqs.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SNSController {

    @Autowired
    private SNSPublisher snsPublisher;

    @PostMapping("/send")
    public void sendMessage(@RequestBody Employee emp){
        snsPublisher.send("test", emp);
    }
}
