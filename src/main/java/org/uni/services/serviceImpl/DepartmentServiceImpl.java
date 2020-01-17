package org.uni.services.serviceImpl;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.uni.dao.DepartmentRepository;
import org.uni.model.Department;
import org.uni.services.DepartmentService;

import javax.transaction.Transactional;
import java.util.List;


public class DepartmentServiceImpl implements DepartmentService {

    public DepartmentServiceImpl(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    private static final String DEPARTMENT_ID_PREFIX = "d";

    private DepartmentRepository departmentRepository;

    public List<Department> getDepartments(int limit) {
        Pageable resultLimit = new PageRequest(0, limit);
        return departmentRepository.findAll(resultLimit).getContent();
    }

    public Department getDepartmentById(String id) {
        return departmentRepository.getOne(id);
    }

    @Transactional
    public Department addDepartment(Department department) {
        Pageable bottomMost = new PageRequest(0, 1, Sort.Direction.DESC, "departmentId");
        List<Department> departments = departmentRepository.findAll(bottomMost).getContent();
        Department lastDepartment = departments.get(0);
        String oldDepartmentId = lastDepartment.getDepartmentId().replace(DEPARTMENT_ID_PREFIX, "");  // Remove prefix

        try {
            int oldId = Integer.parseInt(oldDepartmentId);
            int newId = oldId + 1;
            String newDepartmentId = String.format("%s%03d", DEPARTMENT_ID_PREFIX, newId);
            department.setDepartmentId(newDepartmentId);
            return departmentRepository.save(department);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Department editDepartment(Department department) {

        return departmentRepository.save(department);
    }

    public void deleteDepartment(String id) {
        departmentRepository.deleteById(id);
    }
}
