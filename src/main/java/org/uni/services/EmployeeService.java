package org.uni.services;

import org.uni.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees(int limit);

    Employee getEmployeeById(int employeeNumber);

    List<Employee> findByFirstName(String firstName);

    Employee addEmployee(Employee employee);

    Employee editEmployee(Employee employee);

    void deleteEmployee(int employeeNumber);

}
