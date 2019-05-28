package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.domain.LoginForm;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
	/**
	 * 管理者登録時のフォームクラスのインスタンス化.
	 * 
	 * @return InsertAdministratorForm 管理者登録時のフォーム
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	/**ログイン時のフォームクラスのインスタンス化.
	 * @return LoginForm ログイン時のフォーム
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	@Autowired
	private AdministratorService service;

	@Autowired
	private HttpSession session;

	/**
	 * ログイン画面に遷移.
	 * 
	 * @return String login.htmlの表示
	 */
	@RequestMapping("")
	public String index() {
		return "administrator/login";
	}

	/**
	 * 管理者登録画面に遷移.
	 * 
	 * @return String insert.htmlの表示
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * 管理者情報を登録.
	 * 
	 * @param form 管理者登録画面から送られたフォーム
	 * @return 成功時 ログイン画面へ リダイレクト 失敗時 登録画面にもどる
	 */
	@RequestMapping("/insert")
	public String insert(@Validated InsertAdministratorForm form, BindingResult result) {
		if (result.hasErrors()) {
			return toInsert();
		}

		Administrator administrator = new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		service.insert(administrator);
		return "redirect:/";
	}

	@RequestMapping("/login")
	public String login(LoginForm form,Model model) {
		
		if(true) {
			return index();
		}
		
		
		return "";
	}

}
