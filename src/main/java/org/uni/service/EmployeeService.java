package org.uni.service;

import com.fasterxml.jackson.databind.util.EmptyIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.uni.hibernate.EmployeeRepository;
import org.uni.model.Employee;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployees(int limit) {
        Pageable resultLimit = new PageRequest(0, limit);
        return employeeRepository.findAll(resultLimit).getContent();
    }

    public Employee getEmployeeById(int employeeNumber) {

        return employeeRepository.getOne(employeeNumber);
    }

    public List<Employee> findByFirstName(String firstName){

        return employeeRepository.findByFirstName(firstName);
    }

    public Employee addEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    public Employee editEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int employeeNumber) {

        employeeRepository.delete(employeeNumber);
    }
}
