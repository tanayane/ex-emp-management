package jp.co.sample.repository;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {


	private final static RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER=(rs,i)->{
		Administrator administrator=new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mailAddress"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	public Administrator load(Integer id) {
		return null;
	}
	
	public List<Administrator> findAll(){
		return null;
	}
	
	public Administrator save(Administrator administrator) {
		return null;
	}
	
	public Administrator deleteById(Integer id) {
		return null;
	}
}
