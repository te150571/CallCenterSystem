package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		String uId = customerService.searchCustomerId(lastName, firstName, tel, address);

		// 顧客名取得
		String customerName = customerService.findNameByUid(uId);
		model.addAttribute("customerName", customerName);
		
		// 顧客の問い合わせ情報全検索
		String[][] callData = customerCallService.findAllDataByCid(uId);
		model.addAttribute("callData", callData);
		
		return "operation/inquiry-list";
	}
}
