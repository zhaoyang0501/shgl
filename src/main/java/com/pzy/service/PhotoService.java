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
import com.pzy.entity.Photo;
import com.pzy.repository.PhotoRepository;

@Service
public class PhotoService {
	@Autowired
	private PhotoRepository photoRepository;
	
	public List<Photo> findByGrades(Grades grades){
		return photoRepository.findByGrades(grades);
	}
	public List<Photo> findAll() {
		return (List<Photo>) photoRepository.findAll();
	}

	public Page<Photo> findAll(final int pageNumber, final int pageSize,
			final String name) {
		PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize,
				new Sort(Direction.DESC, "id"));

		Specification<Photo> spec = new Specification<Photo>() {
			public Predicate toPredicate(Root<Photo> root,
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
		Page<Photo> result = (Page<Photo>) photoRepository.findAll(spec,
				pageRequest);
		return result;
	}

	public Page<Photo> findAll(final int pageNumber, final int pageSize,
			final Date start, final Date end) {
		PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize,
				new Sort(Direction.DESC, "id"));
		Specification<Photo> spec = new Specification<Photo>() {
			public Predicate toPredicate(Root<Photo> root,
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
		Page<Photo> result = (Page<Photo>) photoRepository.findAll(spec,
				pageRequest);
		return result;
	}

	public void delete(Long id) {
		photoRepository.delete(id);
	}

	public Photo find(Long id) {
		return photoRepository.findOne(id);
	}

	public void save(Photo photo) {
		photoRepository.save(photo);
	}
}