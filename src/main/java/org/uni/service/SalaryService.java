package org.uni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uni.hibernate.EmployeeRepository;
import org.uni.model.Employee;
import org.uni.model.Salary;

import java.util.List;

@Service
public class SalaryService {


    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Salary> getSalariesById(final int employeeNumber) {
        Employee employee = employeeRepository.findOne(employeeNumber);
        return employee.getSalaries();
    }
}
