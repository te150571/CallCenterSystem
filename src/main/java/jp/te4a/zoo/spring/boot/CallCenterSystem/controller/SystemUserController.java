package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.EmployeeBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.SystemUserBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.EmployeeForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.SystemUserForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.EmployeeService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.SystemUserService;

/*
 * ユーザテスト（ログインテスト用）
 * "user_test"でユーザ追加画面
 * "user_test/user_auto_create"で自動ユーザ追加
 */

@Controller
@RequestMapping("user_test")
public class SystemUserController {
	
	@Autowired
	SystemUserService userService;
	
	@Autowired
	EmployeeService employeeService;
	
	@ModelAttribute
	SystemUserForm setUpForm() {
		return new SystemUserForm();
	}
	
	@GetMapping
	String list(Model model) {
		return "user_test/add";
	}
	
	@GetMapping(path="test-user-create")
	String testUserCreate() {
		// システムユーザデータベース登録
		SystemUserBean userBean = new SystemUserBean("150571", "password", "Name", 0);		
		SystemUserForm userForm = new SystemUserForm();
		BeanUtils.copyProperties(userBean, userForm);
		userService.create(userForm);
		
		return "redirect:/";
	}
	
	@PostMapping(path="create")
	String create(@Validated SystemUserForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return list(model);
		}
		userService.create(form);
		return "redirect:/user_test";
	}
}
