package com.pzy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzy.entity.Sign;
import com.pzy.repository.SignRepository;

@Service
public class SignService {
	@Autowired
	private SignRepository signRepository;
	

	public Sign save(Sign sign) {
		return signRepository.save(sign);
	}
	
}