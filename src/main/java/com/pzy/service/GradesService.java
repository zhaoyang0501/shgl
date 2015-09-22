
package com.pzy.service;

import java.util.Date;
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
import com.pzy.entity.News;
import com.pzy.repository.GradesRepository;

@Service
public class GradesService {
     @Autowired
     private GradesRepository gradesRepository;
     public List<Grades> findTop4() {
    	 PageRequest pageRequest = new PageRequest(0, 4, new Sort(Direction.DESC, "createDate"));
         Specification<Grades> spec = new Specification<Grades>() {
              public Predicate toPredicate(Root<Grades> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              predicate.getExpressions().add(cb.equal(root.get("state").as(String.class),"已审核"));
              return predicate;
              }
         };
         Page<Grades> result = (Page<Grades>) gradesRepository.findAll(spec, pageRequest);
         return result.getContent();
  	}
     public List<Grades> findAll() {
         return (List<Grades>) gradesRepository.findAll();
     }
     public List<Grades> findAllApproved() {
         return (List<Grades>) gradesRepository.findByState("已审核");
     }
     public Page<Grades> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Grades> spec = new Specification<Grades>() {
              public Predicate toPredicate(Root<Grades> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Grades> result = (Page<Grades>) gradesRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Grades> findAll(final int pageNumber, final int pageSize,final Date start,final Date end){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Grades> spec = new Specification<Grades>() {
              public Predicate toPredicate(Root<Grades> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (start != null) {
                  predicate.getExpressions().add(cb.greaterThan(root.get("createDate").as(Date.class),start));
               }
               if (end!=null) {
             	  predicate.getExpressions().add(cb.lessThan(root.get("createDate").as(Date.class),end));
               }
              return predicate;
              }
         };
         Page<Grades> result = (Page<Grades>) gradesRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			gradesRepository.delete(id);
		}
		public Grades find(Long id){
			  return gradesRepository.findOne(id);
		}
		public void save(Grades grades){
			gradesRepository.save(grades);
		}
}