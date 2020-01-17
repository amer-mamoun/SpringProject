package org.uni.services.serviceImpl;

import org.uni.dao.EmployeeRepository;
import org.uni.model.Employee;
import org.uni.model.Salary;
import org.uni.services.SalaryService;

import java.util.List;


public class SalaryServiceImpl implements SalaryService {

    private EmployeeRepository employeeRepository;

    public SalaryServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Salary> getSalariesById(final int employeeNumber) {
        Employee employee = employeeRepository.getOne(employeeNumber);
        return employee.getSalaries();
    }
}
