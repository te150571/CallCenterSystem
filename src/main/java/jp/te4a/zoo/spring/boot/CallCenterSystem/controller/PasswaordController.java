package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.te4a.zoo.spring.boot.CallCenterSystem.service.SystemUserService;

/*
 * パスワード更新コントローラ
 * アドレスは "password"
 */

@Controller
@RequestMapping("password")
public class PasswaordController {
	
	@Autowired
	SystemUserService systemUserService;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new Pbkdf2PasswordEncoder();
	}
	
	// パスワード変更画面
	@RequestMapping
	String firstPassChange() {
		return "operation/pass-change";
	}

	// パスワード変更処理
	@PostMapping("pass-change")
	String passChange(@RequestParam("password") String password, @RequestParam("new_password") String newPassword, @RequestParam("new_password_check") String newPasswordCheck) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uid = userDetails.getUsername();
		// 初回変更であれば初回ログインフラグを更新
		if(systemUserService.getFirstFlag(uid) == 1) {
			systemUserService.updateFirstAccFlag(uid);
		}
		// パスワードが一致するならパスワードを更新
		if(passwordEncoder().matches(password, systemUserService.getPassword(uid)) && newPassword.equals(newPasswordCheck)) {
			systemUserService.updatePassword(uid, newPassword);
		}
		else {
			System.out.println("DEBUG1:");
			return "forward:?error";
		}
		
		return "redirect:/operation";
	}
}
