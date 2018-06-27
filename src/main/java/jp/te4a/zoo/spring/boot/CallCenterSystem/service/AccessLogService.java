package jp.te4a.zoo.spring.boot.CallCenterSystem.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.AccessLogBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.AccessLogForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.repository.AccessLogRepository;

/*
 * AccessLogデータベースService
 * "create" アクセスログ追加
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
}
