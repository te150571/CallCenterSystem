package jp.te4a.zoo.spring.boot.CallCenterSystem.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.AccessLogBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.SystemUserBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.AccessLogForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.repository.SystemUserRepository;
import jp.te4a.zoo.spring.boot.CallCenterSystem.security.LoginUserDetails;
import utilities.IpAddress;
import utilities.MacAddress;

@Service
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	SystemUserRepository systemUserRepository;
	
	@Autowired
	AccessLogService accessLogService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// アクセスログを登録する
		IpAddress ip = new IpAddress();
		MacAddress mac = new MacAddress();
		AccessLogBean accessLogBean = new AccessLogBean();
		accessLogBean.setIp(ip.getIpAddress());
		accessLogBean.setMac(mac.getMacAddress());
		accessLogBean.setUserId(username);
		accessLogBean.setDate(String.valueOf(new Date()));
		accessLogBean.setAccResult(0);
		AccessLogForm accessLogForm = new AccessLogForm();
		BeanUtils.copyProperties(accessLogBean, accessLogForm);
		accessLogService.create(accessLogForm);
		
		// システムユーザデータベースから検索
		Optional<SystemUserBean> userOpt = systemUserRepository.findById(username);
		SystemUserBean user = userOpt.orElseThrow(() -> new UsernameNotFoundException("The request user is not found."));
		
		return new LoginUserDetails(user);
	}
}
