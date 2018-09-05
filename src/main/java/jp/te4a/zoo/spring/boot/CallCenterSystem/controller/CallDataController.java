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

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	String inquiry_create_check(@RequestParam("cId") String cId, @RequestParam(name = "category") String category, @RequestParam(name = "inquiry_input") String inquiry_input, Model model) {	/*@RequestParam("cId") String cId, Model model*/
		System.out.println("cId :"+ cId);
		System.out.println("category :"+ category);
		System.out.println("check_inquiry_input :" + inquiry_input);
		
		model.addAttribute("cId", cId);
		model.addAttribute("category", category);
		model.addAttribute("inquiry_input", inquiry_input);
		
		return "/operation/query_input_check";
	}
	
	
	@RequestMapping(value="registration_back", params="back")
	String inquiry_create_back(@RequestParam("cId") String cId, @RequestParam(name = "category_check") String category_check, @RequestParam(name = "inquiry_input") String inquiry_input, Model model) {
		System.out.println("cId :"+ cId);
		System.out.println("category_check :"+ category_check);
		System.out.println("check_inquiry_input :" + inquiry_input);
		
		model.addAttribute("cId", cId);
		model.addAttribute("category_check", category_check);
		model.addAttribute("inquiry_input", inquiry_input);
		return "/operation/query_input";
	}
	
	
	@RequestMapping(value="registration_back", params="confirm")
	String inquiry_create_registration(@ModelAttribute("customercallForm") CustomerCallForm customercallForm,  Model model) {
		/*
		model.addAttribute("uId", uId);*/
		model.addAttribute("coustomercallForm", customercallForm);
		
		// 日付・日時取得
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat timedate = new SimpleDateFormat("yyyy/MM/dd k:mm");
		System.out.println("model : "+model);
		
		
		
		return "/operation/s-result";
	}
	
}
