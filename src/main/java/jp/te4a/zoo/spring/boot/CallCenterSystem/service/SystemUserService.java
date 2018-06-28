package jp.te4a.zoo.spring.boot.CallCenterSystem.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.SystemUserBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.SystemUserForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.repository.SystemUserRepository;

/*
 * コールセンター顧客管理システムログインユーザデータベースService
 * "create" ユーザ登録（テスト用）
 * "updatePassword" ユーザのパスワードを更新
 * "updateFirstFlag" 初回ログインフラグを更新
 * "getFirstFlag" 初回ログインフラグを取得する
 */

@Service
public class SystemUserService {
	
	@Autowired
	SystemUserRepository systemUserRepository;
	
	public SystemUserForm create(SystemUserForm systemUserForm) {
		systemUserForm.setPassword(new Pbkdf2PasswordEncoder().encode(systemUserForm.getPassword()));
		
		SystemUserBean userBean = new SystemUserBean();
		BeanUtils.copyProperties(systemUserForm, userBean);
		systemUserRepository.save(userBean);
		systemUserRepository.flush();
		return systemUserForm;
	}
	
	public void updatePassword(String uid, String newPass) {
		systemUserRepository.updatePassword(uid, newPass);
		systemUserRepository.flush();
	}
	
	public void updateFirstAccFlag(String uid) {
		systemUserRepository.updateFirstAccFlag(uid);
		systemUserRepository.flush();
	}
	
	public int getFirstFlag(String uid) {
		Optional<SystemUserBean> userOpt = systemUserRepository.findById(uid);
		SystemUserBean systemuUserBean = userOpt.orElseThrow(() -> new UsernameNotFoundException("The request user is not found."));
		return systemuUserBean.getFirstAccFlag();
	}
	
	public String getPassword(String uid) {
		return systemUserRepository.getPassword(uid);
	}
}
