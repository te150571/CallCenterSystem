package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * 顧客データ操作に関するコントローラ
 * アドレスは customer
 */

@Controller
@RequestMapping("customer")
public class CustomerController {

	@RequestMapping
	String customerAdd() {
		return "operation/client-input";
	}

	@RequestMapping("check")
	String customerCheck(
			@RequestParam("lastName") String lastName,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastNameKana") String lastNameKana,
			@RequestParam("firstNameKana") String firstNameKana,
			@RequestParam("address") String address,
			@RequestParam("tel") String tel,
			@RequestParam("birthday") String birthday,
			Model model) {
		
		
		
		return "operation/client-input-check";
	}
}
