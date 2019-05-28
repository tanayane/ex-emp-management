package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Service
public class AdministratorService {

	@Autowired
	private AdministratorRepository repository;
	
	public Administrator load(Integer id) {
		return repository.load(id);
	}
	
	public List<Administrator> findAll(){
		return repository.findAll();
	}
	
	public Administrator save(Administrator administrator) {
		return repository.save(administrator);
	}
	
	public Administrator deleteById(Integer id) {
		return repository.deleteById(id);
	}

}
