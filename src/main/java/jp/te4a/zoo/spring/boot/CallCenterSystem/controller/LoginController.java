package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.LockedIdBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.LockedIdForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.AccessLogService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.LockedIdService;

/*
 * ログイン操作コントローラ
 * アドレスは /
 * ログインロック機能については保留中
 */

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	AccessLogService accessLogService;
	
	@Autowired
	LockedIdService lockedIdService;
	
//	@ModelAttribute
//	LoginForm setUpForm() {
//		return new LoginForm();
//	}
	
	// ログイン画面
	@RequestMapping
	String index() {
		return "login/form";
	}
	
	// ログイン成功時 ( "/success" )
	@RequestMapping("success")
	String loginSuccess() {
		return "redirect:operation";
	}
	
	// ログイン失敗時 ( "/failed" )
	@RequestMapping("failed")
	String loginFailed() {
		// アクセスしてきたIDが連続で5回ミスしていたらロックする
		if(accessLogService.countLoginMiss("") > 4) {
			LockedIdBean lockedIdBean = new LockedIdBean();
			lockedIdBean.setLockedId("id");
			lockedIdBean.setDate(String.valueOf(new Date()));
			lockedIdBean.setLocked(1);
			LockedIdForm lockedIdForm = new LockedIdForm();
			BeanUtils.copyProperties(lockedIdBean, lockedIdForm);
			lockedIdService.create(lockedIdForm);
		}
		
		// アクセスしてきたIDがロックされているかチェック
		if(lockedIdService.lockedCheck("") == "1") {
			return "login/miss";
		}
		else {
			return "forward:?error";
		}
	}
}
