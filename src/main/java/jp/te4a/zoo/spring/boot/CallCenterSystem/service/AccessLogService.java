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
 * "searchLastAccessByIp" 対象IPアドレスの最終ログインログのIDを返す
 * "countLoginMiss" 対象IPアドレスでの直近5回のログイン成否を返す（ログインロック用）
 * "updateAccResult" 対象IPアドレスの最終ログインログの成否フラグを更新する
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
	
	public int searchLastAccessByIp(String ip) {
		return accessLogRepository.findByIpLast(ip);
	}
	
	public int countLoginMiss(String ip) {
		return accessLogRepository.countLoginMiss(ip);
	}
	
	public void updateAccResult(int id) {
		accessLogRepository.updateAccResult(id);
		accessLogRepository.flush();
	}
}
