package jp.te4a.zoo.spring.boot.CallCenterSystem.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.SystemUserBean;
import lombok.Data;

@Data
public class LoginUserDetails extends User {

	private final SystemUserBean user;
	
	public LoginUserDetails(SystemUserBean systemUserBean) {		
		super(systemUserBean.getUsername(), systemUserBean.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.user = systemUserBean;
	}
}
