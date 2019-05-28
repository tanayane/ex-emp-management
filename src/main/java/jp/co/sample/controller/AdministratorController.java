package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
	/**管理者登録時のフォームクラスのインスタンス化.
	 * @return InsertAdministratorForm 管理者登録時のフォーム
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	@Autowired
	private AdministratorService service;
	
	/**ログイン画面に遷移.
	 * @return String  login.htmlの表示
	 */
	@RequestMapping("")
	public String index() {
		return "administrator/login";
	}
	
	/**管理者登録画面に遷移.
	 * @return　String insert.htmlの表示
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	/**管理者情報を登録.
	 * @param form 管理者登録画面から送られたフォーム
	 * @return　ログイン画面へ　リダイレクト
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator=new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		service.insert(administrator);
		return "redirect:/";
	}
	
	
}
