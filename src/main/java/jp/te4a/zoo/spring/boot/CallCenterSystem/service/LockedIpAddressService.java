package jp.te4a.zoo.spring.boot.CallCenterSystem.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.LockedIpAddressBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.LockedIpAddressForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.repository.LockedIpAddressRepository;

/*
 * ロック済みIPアドレスデータベースService
 * "create" ロックIPアドレス追加
 */

@Service
public class LockedIpAddressService {

	@Autowired
	LockedIpAddressRepository lockedIpAddressRepository;
	
public LockedIpAddressForm create(LockedIpAddressForm lockedIpAddressForm) {
		
		LockedIpAddressBean lockedIpAddressBean = new LockedIpAddressBean();
		BeanUtils.copyProperties(lockedIpAddressForm, lockedIpAddressBean);
		lockedIpAddressRepository.save(lockedIpAddressBean);
		lockedIpAddressRepository.flush();
		return lockedIpAddressForm;
	}
}
