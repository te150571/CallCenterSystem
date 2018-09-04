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
	String customerAdd(Model model, @RequestParam(name = "uId" , required = true) String uId) {
		model.addAttribute("uId", uId);
		model.addAttribute("customerForm", new CustomerForm());
		
		return "operation/client-input";
	}

	@RequestMapping("check")
	String customerCheck(@ModelAttribute("customerForm") CustomerForm customerForm, @RequestParam(name= "year") int year, @RequestParam(name = "month") int month, @RequestParam(name = "day") int day, @RequestParam(name = "uId" , required = true) String uId, Model model) {
		model.addAttribute("uId", uId);
		model.addAttribute("customerForm", customerForm);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		
		return "operation/client-input-check";
	}
	
	@RequestMapping("confirm")
	String customerConfirm(@ModelAttribute("customerForm") CustomerForm customerForm, @RequestParam(name= "year") int year, @RequestParam(name = "month") int month, @RequestParam(name = "day") int day, @RequestParam(name="switch") String switchFlag, @RequestParam(name = "uId" , required = true) String uId, Model model) {

		model.addAttribute("uId", uId);
		
		if(switchFlag.equals("redo")) {
			model.addAttribute("customerForm", customerForm);
			model.addAttribute("year", year);
			model.addAttribute("month", month);
			model.addAttribute("day", day);
			
			return "operation/client-input";
		}
		else {
			String maxUId = customerService.getMaxId();
			int maxUIdNum = Integer.parseInt(maxUId);
			maxUIdNum++;
			String nextUId = String.format("%08d", maxUIdNum); 
			customerForm.setId(nextUId);
			
			customerService.create(customerForm);
			
			return "operation/client-input-done";
		}
	}
	
	@RequestMapping("redo")
	String customerRedo(@ModelAttribute("customerForm") CustomerForm customerForm, @RequestParam(name = "uId" , required = true) String uId, Model model) {
		model.addAttribute("uId", uId);
		model.addAttribute("customerForm", customerForm);
		
		return "operation/client-input";
	}
	
	@RequestMapping("edit")
	String customerEdit(@RequestParam("cId") String cId, @RequestParam(name = "uId" , required = true) String uId, Model model) {
		model.addAttribute("uId", uId);
		model.addAttribute("cId", cId);
		
		CustomerForm customerForm = customerService.findById(cId);
		model.addAttribute("customerForm", customerForm);
		
		return "operation/client-input";
	}
	
	@RequestMapping("exit")
	String exit(@RequestParam(name = "next") String next,
			@RequestParam(name = "uId" , required = true) String uId,
			@RequestParam(name="lastName") String lastname,
			@RequestParam(name="firstName") String firstname,
			@RequestParam(name="tel") String tel,
			@RequestParam(name="address") String address,
			Model model) {
		
		model.addAttribute("uId", uId);
		model.addAttribute("lastName", lastname);
		model.addAttribute("firstName", firstname);
		model.addAttribute("tel", tel);
		model.addAttribute("address", address);
		
		if(next.equals("search")) {
			return "redirect:/operation";
		}
		else {
			return "redirect:/operation/searching";
		}
	}
}
