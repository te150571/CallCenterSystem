package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.LockedIpAddressBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.LockedIpAddressForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.AccessLogService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.LockedIpAddressService;
import utilities.IpAddress;

/*
 * ログイン操作コントローラ
 */

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	AccessLogService accessLogService;
	
	@Autowired
	LockedIpAddressService lockedIpAddressService;
	
	// ログイン画面
	@RequestMapping
	String index() {
		IpAddress ipAddress = new IpAddress();
		String ip = ipAddress.getIpAddress();
//		// アクセスしてきたIPアドレスが連続で5回ミスしていたらロックする
//		if(accessLogService.countLoginMiss(ip) > 4) {
//			LockedIpAddressBean lockedIpAddressBean = new LockedIpAddressBean();
//			lockedIpAddressBean.setIp(ip);
//			lockedIpAddressBean.setDate(String.valueOf(new Date()));
//			lockedIpAddressBean.setLocked(1);
//			LockedIpAddressForm lockedIpAddressForm = new LockedIpAddressForm();
//			BeanUtils.copyProperties(lockedIpAddressBean, lockedIpAddressForm);
//			lockedIpAddressService.create(lockedIpAddressForm);
//		}
		
		// アクセスしてきたIPアドレスがロックされているかチェック
		if(lockedIpAddressService.LockedCheck(ip) != null && lockedIpAddressService.LockedCheck(ip).equals("1")) {
			return "login/miss";
		}
		else {
			return "login/form";
		}
	}
	
	// ログイン成功時
	@RequestMapping("success")
	String loginSuccess() {
		return "redirect:operation";
	}
	
	// ログイン失敗時
	@RequestMapping("failed")
	String loginFailed() {
		IpAddress ipAddress = new IpAddress();
		String ip = ipAddress.getIpAddress();
		// アクセスしてきたIPアドレスが連続で5回ミスしていたらロックする
		if(accessLogService.countLoginMiss(ip) > 4) {
			LockedIpAddressBean lockedIpAddressBean = new LockedIpAddressBean();
			lockedIpAddressBean.setIp(ip);
			lockedIpAddressBean.setDate(String.valueOf(new Date()));
			lockedIpAddressBean.setLocked(1);
			LockedIpAddressForm lockedIpAddressForm = new LockedIpAddressForm();
			BeanUtils.copyProperties(lockedIpAddressBean, lockedIpAddressForm);
			lockedIpAddressService.create(lockedIpAddressForm);
		}
		
		// アクセスしてきたIPアドレスがロックされているかチェック
		if(lockedIpAddressService.LockedCheck(ip) != null && lockedIpAddressService.LockedCheck(ip).equals("1")) {
			return "login/miss";
		}
		else {
			return "forward:?error";
		}
	}
	
//	// ログイン連続5回失敗
//	@RequestMapping("locked")
//	String loginFiveMissed() {
//		return "login/miss";
//	}
}
