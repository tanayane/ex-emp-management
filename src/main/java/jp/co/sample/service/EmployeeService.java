package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	public Employee load(Integer id) {
		return repository.load(id);
	}
	
	public List<Employee> findAll(){
		return repository.findAll();
	}
	
	public Employee save(Employee employee) {
		return repository.save(employee);
	}
	
	public Employee deleteById(Integer id) {
		return repository.deleteById(id);
	}

}
