package org.uni.service;

import org.uni.model.Salary;

import java.util.List;

public interface SalaryService {

    List<Salary> getSalariesById(final int employeeNumber);
}
