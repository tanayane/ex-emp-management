package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者関連機能の処理の制御を行うコントローラ.
 * 
 * @author ayane.tanaka
 *
 */
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

	/**
	 * ログイン時のフォームクラスのインスタンス化.
	 * 
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
	 * @return 管理者登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * 管理者情報を登録.
	 * 
	 * @param form 管理者登録画面から送られたフォーム
	 * @return 成功時:ログイン画面へリダイレクト ,失敗時:登録画面にもどる
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

	/**
	 * ログイン処理を行う.
	 * 
	 * @param form 管理者ログイン画面から送られるフォーム
	 * @param　result 入力が間違っている場合のエラーを持つ
	 * @return　ログイン成功時:従業員リストに遷移, 失敗時:ログイン画面に遷移
	 */
	@RequestMapping("/login")
	public String login(@Validated LoginForm form,BindingResult result) {
		Administrator administrator=service.findByMailAddressAndPassword(form.getMailAddress(), form.getPassword());
		
		if(administrator==null) {
			result.rejectValue("mailAddress", null,	"メールアドレスまたはパスワードが間違っています");
			return index();
		}
		session.setAttribute("administratorName",administrator.getName());
		return "forward:/employee/showList";
	}
	
	/**
	 * ログアウト処理を行う.
	 * 
	 * @return ログイン画面へリダイレクト
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}

}
