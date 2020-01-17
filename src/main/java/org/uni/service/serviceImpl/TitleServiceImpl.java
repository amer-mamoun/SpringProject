package org.uni.service.serviceImpl;


import org.uni.dao.EmployeeRepository;
import org.uni.model.Employee;
import org.uni.model.Title;
import org.uni.service.TitleService;

import java.util.List;


public class TitleServiceImpl implements TitleService {


    private EmployeeRepository employeeRepository;

    public TitleServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Title> getTitlesByEmployeeNumber(final int employeeNumber) {
        Employee employee = employeeRepository.getOne(employeeNumber);
        return employee.getTitles();
    }
}
