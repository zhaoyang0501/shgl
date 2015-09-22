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
import com.pzy.entity.MsgBoard;
import com.pzy.entity.Notice;
import com.pzy.repository.NoticeRepository;

@Service
public class NoticeService {
	@Autowired
	private NoticeRepository noticeRepository;
	
	public List<Notice> findByGrades(Grades grades){
		return noticeRepository.findByGrades(grades);
	}
	public List<Notice> findAll() {
		return (List<Notice>) noticeRepository.findAll();
	}

	public Page<Notice> findAll(final int pageNumber, final int pageSize,
			final String name) {
		PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize,
				new Sort(Direction.DESC, "id"));

		Specification<Notice> spec = new Specification<Notice>() {
			public Predicate toPredicate(Root<Notice> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (name != null) {
					predicate.getExpressions().add(
							cb.like(root.get("name").as(String.class), "%"
									+ name + "%"));
				}
				return predicate;
			}
		};
		Page<Notice> result = (Page<Notice>) noticeRepository.findAll(spec,
				pageRequest);
		return result;
	}

	public Page<Notice> findAll(final int pageNumber, final int pageSize,
			final Date start, final Date end) {
		PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize,
				new Sort(Direction.DESC, "id"));
		Specification<Notice> spec = new Specification<Notice>() {
			public Predicate toPredicate(Root<Notice> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (start != null) {
					predicate.getExpressions().add(
							cb.greaterThan(root.get("createDate")
									.as(Date.class), start));
				}
				if (end != null) {
					predicate.getExpressions().add(
							cb.lessThan(root.get("createDate").as(Date.class),
									end));
				}
				return predicate;
			}
		};
		Page<Notice> result = (Page<Notice>) noticeRepository.findAll(spec,
				pageRequest);
		return result;
	}

	public void delete(Long id) {
		noticeRepository.delete(id);
	}

	public Notice find(Long id) {
		return noticeRepository.findOne(id);
	}

	public void save(Notice notice) {
		noticeRepository.save(notice);
	}
}