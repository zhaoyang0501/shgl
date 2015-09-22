package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Grades;
import com.pzy.entity.MsgBoard;
import com.pzy.entity.Notice;
public interface NoticeRepository extends PagingAndSortingRepository<Notice, Long>,JpaSpecificationExecutor<Notice>{
	public List<Notice> findByGrades(Grades grades);
}

