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
	String customerCheck(@ModelAttribute("customerForm") CustomerForm customerForm, @RequestParam(name= "year") int year, @RequestParam(name = "month") int month, @RequestParam(name = "day") int day, Model model) {
		model.addAttribute("customerForm", customerForm);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		
		System.out.println("DEBUG 1 : " + customerForm.getLastname());
		System.out.println("DEBUG 1 : " + customerForm.getFirstname());
		System.out.println("DEBUG 1 : " + customerForm.getLastname_kana());
		System.out.println("DEBUG 1 : " + customerForm.getFirstname_kana());
		System.out.println("DEBUG 1 : " + customerForm.getBirth());
		System.out.println("DEBUG 1 : " + customerForm.getTel());
		System.out.println("DEBUG 1 : " + customerForm.getAddresscode());
		System.out.println("DEBUG 1 : " + customerForm.getAddress());
		System.out.println("DEBUG 1 : " + year);
		System.out.println("DEBUG 1 : " + month);
		System.out.println("DEBUG 1 : " + day);
		
		return "operation/client-input-check";
	}
	
	@RequestMapping("confirm")
	String customerConfirm(@ModelAttribute("customerForm") CustomerForm customerForm, Model model) {
		model.addAttribute("customerForm", customerForm);
		
		String maxUId = customerService.getMaxId();
		int maxUIdNum = Integer.parseInt(maxUId);
		maxUIdNum++;
		String nextUId = String.format("%08d", maxUIdNum); 
		customerForm.setId(nextUId);
		
		System.out.println("DEBUG 2 : " + customerForm.getId());
		System.out.println("DEBUG 2 : " + customerForm.getLastname());
		System.out.println("DEBUG 2 : " + customerForm.getFirstname());
		System.out.println("DEBUG 2 : " + customerForm.getLastname_kana());
		System.out.println("DEBUG 2 : " + customerForm.getFirstname_kana());
		System.out.println("DEBUG 2 : " + customerForm.getBirth());
		System.out.println("DEBUG 2 : " + customerForm.getTel());
		System.out.println("DEBUG 2 : " + customerForm.getAddresscode());
		System.out.println("DEBUG 2 : " + customerForm.getAddress());
		
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
