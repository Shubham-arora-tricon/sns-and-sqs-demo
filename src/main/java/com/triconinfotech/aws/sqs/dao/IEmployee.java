package com.triconinfotech.aws.sqs.dao;

import com.triconinfotech.aws.sqs.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployee extends JpaRepository<Employee, Integer> {
}
