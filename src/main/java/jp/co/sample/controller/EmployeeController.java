package jp.co.sample.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報を操作するサービス .
 * 
 * @author ayane.tanaka
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}

	@Autowired
	private EmployeeService service;

	@Autowired
	private HttpSession session;

	/**
	 * 従業員リスト画面で従業員を全件表示.
	 * 
	 * @return 従業員リスト画面(ログアウト時はログイン画面)
	 */
	@RequestMapping("/showList")
	public String showList(Integer page, Model model) {
		String name = (String) session.getAttribute("administratorName");
		if (name == null) {
			return "redirect:/";
		}
		if (page == null) {
			page = 1;
		}
		List<Employee> employeeList = service.showList();
		List<Employee> fragmentsEmployeeList = new ArrayList<>();
		try {
			for (int i = (page-1)*10+1; i <= page * 10; i++) {
				fragmentsEmployeeList.add(employeeList.get(i));
			}
		}catch(Exception e) {
			
		}finally{
			model.addAttribute("employeeList", fragmentsEmployeeList);
			model.addAttribute("page",page);
			model.addAttribute("size", employeeList.size());
		}
		return "employee/list";
	}

	/**
	 * 指定された従業員情報詳細を表示.
	 * 
	 * @param id    指定された従業員id
	 * @param model リクエストスコープ 従業員情報を格納する
	 * @return 従業員詳細画面(ログアウト時はログイン画面)
	 */
	@RequestMapping("/showDetail")
	public String showDetail(Integer id, Model model) {
		String name = (String) session.getAttribute("administratorName");
		if (name == null) {
			return "redirect:/";
		}
		Employee employee = service.showDetail(id);
		model.addAttribute("employee", employee);
		return "employee/detail";

	}

	/**
	 * 従業員の扶養人数を更新.
	 * 
	 * @param form   更新する従業員idと新たな扶養人数
	 * @param result 入力値チェックの結果
	 * @return 従業員リスト画面(ログアウト時はログイン画面)
	 */
	@RequestMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
		String name = (String) session.getAttribute("administratorName");
		if (name == null) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			return showDetail(form.getId(), model);
		}
		Employee employee = service.showDetail(form.getId());
		BeanUtils.copyProperties(form, employee);
		service.update(employee);
		return "forward:/employee/showList";

	}

}
