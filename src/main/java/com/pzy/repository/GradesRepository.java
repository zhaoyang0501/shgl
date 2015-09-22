package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Grades;
public interface GradesRepository extends PagingAndSortingRepository<Grades, Long>,JpaSpecificationExecutor<Grades>{
	public List<Grades> findByState(String state);
}

