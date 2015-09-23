
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

import com.pzy.entity.Contract;
import com.pzy.entity.Sign;
import com.pzy.entity.User;
import com.pzy.repository.ContractRepository;

@Service
public class ContractService {
     @Autowired
     private ContractRepository contractRepository;
    
     public List<Contract> findNotSign(User user){
    	 return this.contractRepository.findNotSign(user);
     }
 	 public List<Sign> findSigned(User user){
 		 return this.contractRepository.findSigned(user);
 	 }
 	
     public List<Contract> findAll() {
         return (List<Contract>) contractRepository.findAll();
     }
     public Page<Contract> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Contract> spec = new Specification<Contract>() {
              public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Contract> result = (Page<Contract>) contractRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Contract> findAll(final int pageNumber, final int pageSize,final Date start,final Date end){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Contract> spec = new Specification<Contract>() {
              public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
         Page<Contract> result = (Page<Contract>) contractRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			contractRepository.delete(id);
		}
		public Contract find(Long id){
			  return contractRepository.findOne(id);
		}
		public void save(Contract contract){
			contractRepository.save(contract);
		}
}