package com.triconinfotech.aws.sqs.listener;

import com.triconinfotech.aws.sqs.entity.Employee;
import com.triconinfotech.aws.sqs.service.EmployeeService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
@EnableSqs
public class SQSListener {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Employee employee;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeService employeeService;

    @SqsListener(value = "test-queue", deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    public void getMessage(@Payload String msg) {

            try {
                logger.info("Payload from SNS is {}", msg);
                JSONObject jsonObject = new JSONObject(msg);
                String message = (jsonObject.get("Message")).toString();
                JSONObject jsonObject1 = new JSONObject(message);
                employee.setEmpId((Integer) jsonObject1.get("empId"));
                employee.setEmpName(jsonObject1.get("empName").toString());
                employee.setSalary((Long) jsonObject1.get("salary"));
                employee.setDepartment(jsonObject1.get("department").toString());
                Object timestamp = jsonObject.get("Timestamp");
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
                    Date parsedDate = dateFormat.parse(timestamp.toString());
                    Timestamp timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
                    System.out.println("TimeStamp"+timestamp1);
                    employee.setTimestamp(timestamp1);
                } catch(ParseException e) {
                    System.out.println("Excep" + e);
                }
                logger.info("Message {} and TimeStamp {} Extracted from Payload", message, timestamp);
            } catch (JSONException err) {
                logger.error("Exception during listening the msg with value", err);
            }
            logger.info("Employee data we are sending to service wit value {}", employee);
            logger.info("Employee has to be added by Listener with value {}", employee);
            employeeService.addEmployee(employee);
            logger.info("Employee added successfully with value {}", employee);
            logger.info("Emloyee added to database successfully");
    }

}
