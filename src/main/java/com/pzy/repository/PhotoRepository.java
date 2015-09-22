package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Grades;
import com.pzy.entity.Photo;
public interface PhotoRepository extends PagingAndSortingRepository<Photo, Long>,JpaSpecificationExecutor<Photo>{
	public List<Photo> findByGrades(Grades grades);
}

