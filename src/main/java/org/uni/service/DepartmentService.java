package org.uni.service;

import org.uni.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getDepartments(int limit);

    Department getDepartmentById(String id);

    Department addDepartment(Department department);

    Department editDepartment(Department department);

    void deleteDepartment(String id);
}
