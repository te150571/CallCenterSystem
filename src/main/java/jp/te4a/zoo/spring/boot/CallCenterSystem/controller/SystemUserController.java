package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.te4a.zoo.spring.boot.CallCenterSystem.form.SystemUserForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.SystemUserService;

/*
 * ユーザテスト（ログインテスト用）
 * "user_test"でユーザ追加画面
 * "user_test/user_auto_create"で自動ユーザ追加
 */

@Controller
@RequestMapping("usertest")
public class SystemUserController {
	
	@Autowired
	SystemUserService userService;
	
	@ModelAttribute
	SystemUserForm setUpForm() {
		return new SystemUserForm();
	}
	
	@GetMapping
	String createForm() {
		return "user_test/add";
	}
	
	// ユーザ追加 ( "/usertest/create" )
	@PostMapping("create")
	String create(@Validated SystemUserForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return createForm();
		}
		userService.create(form);
		return "redirect:/usertest";
	}
}
