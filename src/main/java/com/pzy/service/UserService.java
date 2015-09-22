
package com.pzy.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pzy.entity.Grades;
import com.pzy.entity.Notice;
import com.pzy.entity.User;
import com.pzy.repository.UserRepository;

@Service
public class UserService {
     @Autowired
     private UserRepository userRepository;
     public List<User> findAll() {
          return (List<User>) userRepository.findAll();
     }
     public List<User> findByNews(Grades grades){
  		return userRepository.findByGradesOrderByCreateDateDesc(grades);
  	}
     public List<User> findByGrades(Grades grades){
 		return userRepository.findByGrades(grades);
 	}
     public Page<User> findAll(final int pageNumber, final int pageSize,final String userName){
               PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
              
               Specification<User> spec = new Specification<User>() {
                    @Override
                    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    Predicate predicate = cb.conjunction();
                    if (userName != null) {
                         predicate.getExpressions().add(cb.like(root.get("userName").as(String.class), userName+"%"));
                    }
                    return predicate;
                    }
               };
               Page<User> result = (Page<User>) userRepository.findAll(spec, pageRequest);
               return result;
     }
     public void delete(String id){
          userRepository.delete(id);
     }
     public User find(String id){
    	  return userRepository.findOne(id);
     }
     public void save(User User){
    	 userRepository.save(User);
     }
     public User login(String id,String password){
    	 List<User> users=userRepository.findByIdAndPassword(id,password);
    	 return users.size()==0?null:users.get(0);
     }
}