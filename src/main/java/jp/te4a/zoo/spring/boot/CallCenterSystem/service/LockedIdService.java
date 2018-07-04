package jp.te4a.zoo.spring.boot.CallCenterSystem.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.LockedIdBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.LockedIdForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.repository.LockedIdRepository;

/*
 * ロック済みIPアドレスデータベースService
 * "create" ロックIPアドレス追加
 * "LockedCheck" ユーザIDがロックされているか
 */

@Service
public class LockedIdService {

	@Autowired
	LockedIdRepository lockedIdRepository;

	public LockedIdForm create(LockedIdForm lockedIdForm) {

		LockedIdBean lockedIdBean = new LockedIdBean();
		BeanUtils.copyProperties(lockedIdForm, lockedIdBean);
		lockedIdRepository.save(lockedIdBean);
		lockedIdRepository.flush();
		return lockedIdForm;
	}
	
	public String LockedCheck(String uId) { 
		
		return lockedIdRepository.lockedCheckByUId(uId);
	}
}
