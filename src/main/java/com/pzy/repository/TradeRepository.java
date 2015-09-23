package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Trade;
import com.pzy.entity.User;
public interface TradeRepository extends PagingAndSortingRepository<Trade, Long>,JpaSpecificationExecutor<Trade>{
public List<Trade> findByUserAndType(User user,String type);
}

