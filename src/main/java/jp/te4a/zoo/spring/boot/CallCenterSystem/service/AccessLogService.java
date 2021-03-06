package jp.te4a.zoo.spring.boot.CallCenterSystem.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.AccessLogBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.AccessLogForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.repository.AccessLogRepository;

/*
 * AccessLogデータベースService
 * "create" データ追加
 * 各メソッドはRepository参照
 */

@Service
public class AccessLogService {

	@Autowired
	AccessLogRepository accessLogRepository;
	
	public AccessLogForm create(AccessLogForm accessLogForm) {
		
		AccessLogBean accessLogBean = new AccessLogBean();
		BeanUtils.copyProperties(accessLogForm, accessLogBean);
		accessLogRepository.save(accessLogBean);
		accessLogRepository.flush();
		return accessLogForm;
	}
	
	public int searchLastAccessByUId(String uId) {
		return accessLogRepository.findByUIdLast(uId);
	}
	
	public int countLoginMiss(String uId) {
		return accessLogRepository.countLoginMiss(uId);
	}
	
	public void updateAccResult(int id) {
		accessLogRepository.updateAccResult(id);
		accessLogRepository.flush();
	}
}
