package org.uni.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.uni.model.Department;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DepartmentRepository extends JpaRepository<Department, String>, CrudRepository<Department, String> {

}
