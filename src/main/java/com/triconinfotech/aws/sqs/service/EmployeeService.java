package com.triconinfotech.aws.sqs.service;

import com.triconinfotech.aws.sqs.dao.IEmployee;
import com.triconinfotech.aws.sqs.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IEmployee iEmployee;

    int count = 0;

    List<Employee> filterList = new ArrayList<>();

    public List<Employee> getListOfEmployees() {
        final List<Employee> all = iEmployee.findAll();
        return all;
    }

    public void addEmployee(Employee emp){
        logger.info("Service starts with payload of Employee.... {}", emp);
        if(this.count < 10) {
            logger.info("Counter condition starts......");
            logger.debug("Count is {} and value added to the list is {}", count, emp);
            filterList.add(emp);
            this.count++;
            logger.debug("counter is updated with {}", count);
            logger.info("Counter condition ends......");
        }
        else if(count == 10){
            logger.info("Counter with expected value 10......");
            logger.debug("filter function calls with value of counter {}", count);
            filter();
            logger.debug("saving of Employee records starts... with value {}", filterList);
            iEmployee.saveAll(filterList);
            logger.info("Employee List Saved with value {}", filterList);
        }


//        iEmployee.save(emp);
    }

    private void filter() {
        logger.info("filter function starts.....");
        for(int i=0; i< filterList.size()-1; i++) {
            Employee employee = filterList.get(i);
            for(int j=i+1;j<filterList.size();j++){
                if(employee.equals(filterList.get(j))){
                    filterList.remove(j);
                }
            }
        }
        logger.info("filter function ends.....with list {}", filterList);
    }
}
