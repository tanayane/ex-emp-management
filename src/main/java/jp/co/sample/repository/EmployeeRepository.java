package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

@Repository
public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
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
		String sql = "select id,name,image,gender,hire_date,mail_address,"
				+ "zip_code,address,telephone,salary,characteristics,dependents_count" + " from employees where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
	}

	public List<Employee> findAll() {
		String sql = "select id,name,image,gender,hire_date,mail_address,"
				+ "zip_code,address,telephone,salary,characteristics,dependents_count "
				+ "from employees order by hire_date";
		return template.query(sql, EMPLOYEE_ROW_MAPPER);
	}

	public void update(Employee employee) {
		String sql = "update employees set dependents_count=:dependentsCount where id=:id";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("dependentsCount", employee.getDependentsCount()).addValue("id", employee.getId());
		template.update(sql, param);
		return;
	}

}
