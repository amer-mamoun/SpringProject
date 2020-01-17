package org.uni.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.uni.model.Salary;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SalaryRepository extends JpaRepository<Salary, Integer>, CrudRepository<Salary, Integer> {
}
