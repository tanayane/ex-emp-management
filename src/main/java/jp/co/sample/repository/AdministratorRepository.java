package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * 管理者テーブルを操作するリポジトリ.
 * 
 * @author ayane.tanaka
 *
 */
@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * DBから受け取ったデータの格納.
	 */
	private final static RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	/**
	 * 新規管理者を登録する.
	 * 
	 * @param administrator　登録する管理者情報
	 */
	public void insert(Administrator administrator) {
		String sql = "insert into administrators(name,mail_address,password) values (:name,:mailAddress,:password)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", administrator.getName())
				.addValue("mailAddress", administrator.getMailAddress())
				.addValue("password", administrator.getPassword());
		template.update(sql, param);
		System.out.println("登録完了");
		return;
	}

	/**
	 * メールアドレスとパスワードから管理者情報を検索.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password　パスワード
	 * @return　存在する場合はデータを、存在しない場合はnullを返す
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "select id,name,mail_address,password from administrators where mail_address= :mail and password= :pass";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mail", mailAddress).addValue("pass", password);
		try {
			Administrator administrator=template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
			return administrator;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
