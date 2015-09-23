package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Sign;
public interface SignRepository extends PagingAndSortingRepository<Sign, Long>,JpaSpecificationExecutor<Sign>{
}

