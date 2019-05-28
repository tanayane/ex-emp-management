package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報を操作するサービス	.
 * 
 * @author ayane.tanaka
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	/**
	 * 従業員リスト画面で従業員を全件表示.
	 * 
	 * @return　従業員リスト画面
　	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList=service.showList();
		model.addAttribute("employeeList",employeeList);
		return "employee/list";
	}
	
	/**
	 * 指定された従業員情報詳細を表示.
	 * 
	 * @param id 指定された従業員id
	 * @param model リクエストスコープ　従業員情報を格納する
	 * @return　従業員詳細画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(Integer id,Model model) {
		Employee employee=service.showDetail(id);
		model.addAttribute("employee", employee);
		return "employee/detail";
		
	}
	
	@RequestMapping("/update")
	public String update(Integer id,Integer dependentsCount) {
		Employee employee=new Employee();
		employee.setId(id);
		employee.setDependentsCount(dependentsCount);
		service.update(employee);
		return "forward:/employee/showList";
		
	}
	
	
}
