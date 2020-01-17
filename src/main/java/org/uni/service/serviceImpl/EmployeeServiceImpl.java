package org.uni.service.serviceImpl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.uni.dao.EmployeeRepository;
import org.uni.model.Employee;
import org.uni.service.EmployeeService;

import java.util.List;


public class EmployeeServiceImpl implements EmployeeService {

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

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

        employeeRepository.deleteById(employeeNumber);
    }
}
