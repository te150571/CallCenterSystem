package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * ログイン操作コントローラ
 */

@Controller
@RequestMapping("/")
public class LoginController {
	
	// ログイン画面
	@RequestMapping
	String index() {
		return "login/miss";
	}
	
	// ログイン成功時
	@RequestMapping("success")
	String loginSuccess() {
		return "forward:operation";
	}
	
//	// ログイン失敗時
//	@RequestMapping("failed")
//	String loginFailed() {
//		System.out.println("DEBUG1:");
//		return "login/form?error";
//	}
	
	// ログイン連続5回失敗
	@RequestMapping("locked")
	String loginFiveMissed() {
		return "login/miss";
	}
}
