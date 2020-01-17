package org.uni.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.uni.model.Title;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TitleRepository extends JpaRepository<Title, Integer>, CrudRepository<Title, Integer> {

}
