package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerCallBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.CustomerCallForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.CustomerCallService;

/*
 * 問い合わせ情報に関するコントローラ
 * アドレスは calldata
 */

@Controller
@RequestMapping("calldata")
public class CallDataController {
	@Autowired
	CustomerCallService customerCallService;
	
	@RequestMapping("create")
	String inquiry_create(@RequestParam("cId") String cId, Model model) {
		
		//顧客IDをmodelに追加
		model.addAttribute("cId", cId);
		return "/operation/query_input";
	}
	
	@RequestMapping("check")
	String inquiry_create_check() {	/*@RequestParam("cId") String cId, Model model*/
		return "/operation/query_input_check";
	}
	
	
	@RequestMapping(value="registration_back", params="back")
	String inquiry_create_back() {
		return "/operation/query_input";
	}
	
	@RequestMapping(value="registration_back", params="registration")
	String inquiry_create_registration() {
		return "/operation/client-inout-done";
	}
	
}
