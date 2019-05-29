package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.form.ModifyAdministratorForm;
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

	@ModelAttribute
	public ModifyAdministratorForm serUpModifyAdministratorForm() {
		return new ModifyAdministratorForm();
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
		String name=(String)session.getAttribute("administratorName");
		if(name!=null) {
			return "forward:/employee/showList";
		}
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
	 * @param result 入力が間違っている場合のエラーを持つ
	 * @return ログイン成功時:従業員リストに遷移, 失敗時:ログイン画面に遷移
	 */
	@RequestMapping("/login")
	public String login(@Validated LoginForm form, BindingResult result) {
		Administrator administrator = service.findByMailAddressAndPassword(form.getMailAddress(), form.getPassword());

		if (administrator == null) {
			result.rejectValue("mailAddress", null, "メールアドレスまたはパスワードが間違っています");
			return index();
		}
		session.setAttribute("administratorName", administrator.getName());
		session.setAttribute("administratorId", administrator.getId());
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
	
	/**
	 * 管理者情報を表示.
	 * 
	 * @param form 管理者情報のフォーム
 	 * @param model リクエストスコープ
	 * @return 管理者情報表示画面(ログアウト時はログイン画面)
	 */
	@RequestMapping("/infomation")
	public String information(ModifyAdministratorForm form,Model model) {
		String name=(String)session.getAttribute("administratorName");//ログインチェック
		if(name==null) {
			return "redirect:/";
		}
		Integer id=(Integer)session.getAttribute("administratorId");
		BeanUtils.copyProperties(service.findById(id),form );
		form.setValidatedPassword(form.getPassword());
		return "/administrator/information";
	}
	
	/**
	 * 管理者情報を更新.
	 * 
	 * @param form 新しい管理者情報
	 * @param result 入力チェック
	 * @param model リクエストスコープ
	 * @return 成功失敗どちらも管理者情報表示画面
	 */
	@RequestMapping("/modify")
	public String modify(@Validated ModifyAdministratorForm form,BindingResult result,Model model) {
		String name=(String)session.getAttribute("administratorName");  //ログインチェック
		if(name==null) {
			return "redirect:/";
		}
		Administrator administrator= service.findById(form.getId());
		if (result.hasErrors()) { 										 //入力チェック
			BeanUtils.copyProperties(form, administrator);
			if(result.hasFieldErrors("password")) {
				form.setValidatedPassword("");	
			}
			return information(form,model);
		}
		if (!form.getPassword().equals(form.getValidatedPassword())) { //パスワード確認チェック
			BeanUtils.copyProperties(form, administrator);
			result.rejectValue("validatedPassword", null, "上記と同じパスワードを入力してください");
			return information(form,model);
		}

		BeanUtils.copyProperties(form, administrator);
		service.update(administrator);
		result.rejectValue("id" ,null,"情報が更新されました");
		return information(form,model);
		
	}

}
