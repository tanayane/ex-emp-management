package jp.co.sample.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository repository;
	
	/**
	 * 管理者情報登録.
	 * 
	 * @param administrator 新規管理者情報
	 */
	public void insert(Administrator administrator) {
		repository.insert(administrator);
	}
	
	/**
	 * メールアドレスとパスワードから管理者情報を検索.
	 * 
	 * @param mailAddress 入力されたメールアドレス
 	 * @param password　入力されたパスワード
	 * @return　一致した管理者情報またはnull
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress,String password){	
		return repository.findByMailAddressAndPassword(mailAddress, password);
	}
	
	/**
	 * idから管理者情報を検索.
	 * 
	 * @param id ログイン中の管理者のid
	 * @return　一致した管理者情報またはnull
	 */
	public Administrator findById(Integer id) {
		return repository.findById(id);
	}

	/**
	 * 管理者情報を更新.
	 * 
	 * @param administrator 更新したい情報
	 */
	public void update(Administrator administrator) {
		repository.update(administrator);
	}

}
