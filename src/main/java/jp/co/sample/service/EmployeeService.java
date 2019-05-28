package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービス.
 * 
 * @author ayane.tanaka
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	/**
	 * 指定されたidの従業員情報を取得.
	 * 
	 * @param id 指定されたid
	 * @return 指定されたidの従業員情報
	 */
	public Employee showDetail(Integer id) {
		return repository.load(id);
	}
	
	/**
	 * 全従業員情報を得る.
	 * 
	 * @return 全従業員情報のリスト
	 */
	public List<Employee> showList(){
		return repository.findAll();
	}
	
	/**
	 * 従業員の扶養人数を更新.
	 * 
	 * @param employee 更新したい従業員情報
	 */
	public void update(Employee employee) {
		repository.update(employee);
	}

}
