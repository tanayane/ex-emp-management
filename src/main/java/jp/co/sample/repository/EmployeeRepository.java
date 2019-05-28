package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * 従業員テーブルを操作するリポジトリ.
 * 
 * @author user
 *
 */
@Repository
public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/** DBから受け取ったデータの格納 */
	private final static RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date").toLocalDate());
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};

	/**
	 * idの一致する従業員データ検索.
	 * 
	 * @param id 検索したいid
	 * @return　Employee 従業員情報　
	 * @throws IncorrectResultSizeDataAccessException 1件も存在しないときに発生
	 */
	public Employee load(Integer id) {
		String sql = "select id,name,image,gender,hire_date,mail_address,"
				+ "zip_code,address,telephone,salary,characteristics,dependents_count" 
				+ " from employees where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
	}

	/**
	 * 全従業員のデータ検索.
	 * 
	 * @return List<Employee> 従業員一覧
	 */
	public List<Employee> findAll() {
		String sql = "select id,name,image,gender,hire_date,mail_address,"
				+ "zip_code,address,telephone,salary,characteristics,dependents_count "
				+ "from employees order by hire_date";
		return template.query(sql, EMPLOYEE_ROW_MAPPER);
	}

	/**従業員の扶養人数を更新.
	 * 
	 * @param employee 更新したい従業員情報
	 */
	public void update(Employee employee) {
		String sql = "update employees set dependents_count=:dependentsCount where id=:id";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("dependentsCount", employee.getDependentsCount()).addValue("id", employee.getId());
		template.update(sql, param);
		return;
	}

}
