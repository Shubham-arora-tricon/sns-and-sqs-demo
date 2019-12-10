package com.triconinfotech.aws.sqs.web;
import com.triconinfotech.aws.sqs.entity.Employee;
import com.triconinfotech.aws.sqs.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SQSController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.url}")
    private String endpoint;


    @PostMapping("/messages")
    public void sendMessage(@RequestBody Employee employee) {
        logger.info("Employee added with value {}",employee);
        queueMessagingTemplate.convertAndSend(endpoint, /*MessageBuilder.withPayload("Shubham Arora").build()*/ employee);
    }

    @GetMapping("/all")
    public List<Employee> getListOfEmployees() {
        final List<Employee> listOfEmployees = employeeService.getListOfEmployees();
        return listOfEmployees;
    }

}
