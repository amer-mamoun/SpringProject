package org.uni.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.uni.dao.DepartmentRepository;
import org.uni.dao.EmployeeRepository;
import org.uni.services.DepartmentService;
import org.uni.services.EmployeeService;
import org.uni.services.SalaryService;
import org.uni.services.TitleService;
import org.uni.services.serviceImpl.DepartmentServiceImpl;
import org.uni.services.serviceImpl.EmployeeServiceImpl;
import org.uni.services.serviceImpl.SalaryServiceImpl;
import org.uni.services.serviceImpl.TitleServiceImpl;

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
