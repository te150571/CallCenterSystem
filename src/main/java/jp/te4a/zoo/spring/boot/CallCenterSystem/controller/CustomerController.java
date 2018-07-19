package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.te4a.zoo.spring.boot.CallCenterSystem.form.CustomerForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.CustomerService;

/*
 * 顧客データ操作に関するコントローラ
 * アドレスは customer
 */

@Controller
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;

	@RequestMapping
	String customerAdd(Model model) {
		model.addAttribute("customerForm", new CustomerForm());
		
		return "operation/client-input";
	}

	@RequestMapping("check")
	String customerCheck(@ModelAttribute("customerForm") CustomerForm customerForm, Model model) {
		model.addAttribute("customerForm", customerForm);
		
		return "operation/client-input-check";
	}
	
	@RequestMapping("confirm")
	String customerConfirm(@ModelAttribute("customerForm") CustomerForm customerForm, Model model) {
		model.addAttribute("customerForm", customerForm);
		
		customerService.create(customerForm);
		
		return "operation/client-input-done";
	}
	
	@RequestMapping("redo")
	String customerRedo(@ModelAttribute("customerForm") CustomerForm customerForm, Model model) {
		model.addAttribute("customerForm", customerForm);
		
		return "operation/client-input";
	}
	
	@RequestMapping("edit")
	String customerEdit(@RequestParam("cId") String cId, Model model) {
		model.addAttribute("cId", cId);
		
		CustomerForm customerForm = customerService.findById(cId);
		model.addAttribute("customerForm", customerForm);
		
		return "operation/client-input";
	}
}
