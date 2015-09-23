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

import com.pzy.entity.Trade;
import com.pzy.entity.User;
import com.pzy.repository.TradeRepository;

@Service
public class TradeService {
	@Autowired
	private TradeRepository tradeRepository;
	
	
	public Page<Trade> findAll(final int pageNumber, final int pageSize,
			final String name) {
		PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize,
				new Sort(Direction.DESC, "id"));

		Specification<Trade> spec = new Specification<Trade>() {
			public Predicate toPredicate(Root<Trade> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (name != null) {
					predicate.getExpressions().add(
							cb.like(root.get("name").as(String.class),"%"+ name
									+ "%"));
				}
				return predicate;
			}
		};
		Page<Trade> result = (Page<Trade>) tradeRepository.findAll(
				spec, pageRequest);
		return result;
	}
	public Trade save(Trade trade) {
		return tradeRepository.save(trade);
	}
	public Trade find(Long id) {
		return tradeRepository.findOne(id);
	}

	public void delete(Long id) {
		Trade trade=this.tradeRepository.findOne(id);
		tradeRepository.delete(id);
	}

	public List<Trade> findHot() {
		return tradeRepository.findAll(
				new PageRequest(0, 6, new Sort(Direction.ASC, "count")))
				.getContent();
	}

	public List<Trade> findByUserAndType(User user,String type) {
		return tradeRepository.findByUserAndType(user, type);
	}
}