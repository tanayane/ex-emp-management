package jp.co.sample.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Service
public class AdministratorService {

	@Autowired
	private AdministratorRepository repository;
	
	public void insert(Administrator administrator) {
		repository.insert(administrator);
		return;
	}
	
	public Administrator findByMailAddressAndPassword(String mailAddress,String password){	
		return repository.findByMailAddressAndPassword(mailAddress, password);
	}

}
