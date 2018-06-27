package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.te4a.zoo.spring.boot.CallCenterSystem.form.CustomerForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.SystemUserForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.CustomerService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.LoginUserDetailsService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.SystemUserService;

/*
 * 本システム用コントローラ
 * アドレスは operation
 */

@Controller
@RequestMapping("operation")
public class CustomerMgController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	SystemUserService systemUserService;
	
	@ModelAttribute
	SystemUserForm setUpForm() {
		return new SystemUserForm();
	}
	
	// ログイン後に表示
	@PostMapping()
	String list(Model model) {
		// ログイン成功時アクセスログの成功フラグを1にする
		
		
		// 初回ログインであればページパスワード変更画面を表示
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(systemUserService.getFirstFlag(userDetails.getUsername()) == 1) {
			return "operation/pass-change";
		}
		else {
			return "operation/search";
		}
	}
	
	// 検索が実行されたとき
	@PostMapping(path="searched")
	String SaerchResult(@Validated CustomerForm form, BindingResult result, Model model) {
//		if(result.hasErrors()) {
//			return list(model);
//		}
//		customerService.create(form);
		return "operation/searchResult";
	}
}