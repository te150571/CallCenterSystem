package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerCallBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.SystemUserForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.AccessLogService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.CustomerCallService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.CustomerService;
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
	CustomerCallService customerCallService;

	@Autowired
	SystemUserService systemUserService;

	@Autowired
	AccessLogService accessLogService;

	@ModelAttribute
	SystemUserForm setUpForm() {
		return new SystemUserForm();
	}

	// ログイン後に表示
	@RequestMapping
	String list() {
		// 初回ログインであればページパスワード変更画面を表示
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uId = userDetails.getUsername();
		
		// ログイン成功時アクセスログの成功フラグを1にする
		int logId = accessLogService.searchLastAccessByUId(uId);
		accessLogService.updateAccResult(logId);

		if(systemUserService.getFirstFlag(uId) == 1) {
			return "redirect:password";
		}
		else {
			return "operation/search";
		}
	}


	// 検索が実行されたとき
	@RequestMapping("searching")
	String SaerchResult(@RequestParam(name = "lastName", required = true) String lastName, @RequestParam(name = "firstName", required = true) String firstName, @RequestParam(name = "tel", required = true) String tel, @RequestParam(name = "address" , required = true) String address, Model model) throws Exception {
		
		System.out.println("DEBUG1: lastName = " + lastName);
		System.out.println("DEBUG1: firstName = " + firstName);
		System.out.println("DEBUG1: tel = " + tel);
		System.out.println("DEBUG1: address = " + address);
		
		String uId = customerService.searchCustomerId(lastName, firstName, tel, address);
		
		System.out.println("DEBUG2: userId = " + uId);
		
		List<CustomerCallBean> callData = customerCallService.findAllDataByCid(uId);
//		System.out.println("DEBUG2-x:" + callData.getClass());
//		CustomerCallBean cb = callData.get(0);
		
		System.out.println("DEBUG2: callData Size = " + callData.size());
//		System.out.println("DEBUG2: callData id = " + cb.getContents());
		
		model.addAttribute("calldata", callData);
		
		return "operation/s-result";
	}
}
