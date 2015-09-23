package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Contract;
import com.pzy.entity.Sign;
import com.pzy.entity.User;
public interface ContractRepository extends PagingAndSortingRepository<Contract, Long>,JpaSpecificationExecutor<Contract>{
	@Query("select contract from Contract contract where  contract not in ( select sign.contract from  Sign sign where  sign.user=?1)")
	public List<Contract> findNotSign(User user);
	@Query("select sign from  Sign sign where  sign.user=?1")
	public List<Sign> findSigned(User user);
	
}

