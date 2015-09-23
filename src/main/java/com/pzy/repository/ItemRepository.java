package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Item;
public interface ItemRepository extends PagingAndSortingRepository<Item, Long>,JpaSpecificationExecutor<Item>{
}

