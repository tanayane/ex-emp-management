package jp.co.sample.repository;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;
import jp.co.sample.domain.Employee;

@Repository
public class EmployeeRepository {


	private final static RowMapper<Employee> ADMINISTRATOR_ROW_MAPPER=(rs,i)->{
		Employee employee=new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date").toLocalDate());
		employee.setMailAddress(rs.getString("mailAddress"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};
	
	public Employee load(Integer id) {
		return null;
	}
	
	public List<Employee> findAll(){
		return null;
	}
	
	public Employee save(Employee employee) {
		return null;
	}
	
	public Employee deleteById(Integer id) {
		return null;
	}
}
