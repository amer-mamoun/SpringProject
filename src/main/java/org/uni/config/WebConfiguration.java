package org.uni.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.uni.dao.DepartmentRepository;
import org.uni.dao.EmployeeRepository;
import org.uni.service.DepartmentService;
import org.uni.service.EmployeeService;
import org.uni.service.SalaryService;
import org.uni.service.TitleService;
import org.uni.service.serviceImpl.DepartmentServiceImpl;
import org.uni.service.serviceImpl.EmployeeServiceImpl;
import org.uni.service.serviceImpl.SalaryServiceImpl;
import org.uni.service.serviceImpl.TitleServiceImpl;

@SpringBootConfiguration
public class WebConfiguration {

    @Bean
    public EmployeeService employeeService(EmployeeRepository employeeRepository){
        return new EmployeeServiceImpl(employeeRepository);
    }

    @Bean
    public DepartmentService departmentService(DepartmentRepository departmentRepository){
        return new DepartmentServiceImpl(departmentRepository);
    }

    @Bean
    public SalaryService salaryService(EmployeeRepository employeeRepository){
        return new SalaryServiceImpl(employeeRepository);
    }

    @Bean
    public TitleService titleService(EmployeeRepository employeeRepository){
        return new TitleServiceImpl(employeeRepository);
    }
}
