package org.uni.services;

import org.uni.model.Salary;

import java.util.List;

public interface SalaryService {

    List<Salary> getSalariesById(final int employeeNumber);
}
