package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Grades;
import com.pzy.entity.News;
import com.pzy.entity.Notice;
import com.pzy.entity.User;
public interface UserRepository extends PagingAndSortingRepository<User, String>,JpaSpecificationExecutor<User>{
    public List<User> findByIdAndPassword(String id,String password);
    public List<User> findByGrades(Grades grades);
    public List<User> findByGradesOrderByCreateDateDesc(Grades grades);
}

