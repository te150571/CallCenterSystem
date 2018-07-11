package jp.te4a.zoo.spring.boot.CallCenterSystem.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerCallBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.CustomerCallForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.repository.CustomerCallRepository;

/*
 * 顧客問い合わせデータベースService
 * "create" 新規問い合わせ登録
 * "findAllDataByCid" ユーザIDから問い合わせ情報の検索
 * "findByAfterDate" ユーザIDから問い合わせ情報の検索（件名指定なし、指定日付以降）
 * "findByBeforeDate" ユーザIDから問い合わせ情報の検索（件名指定なし、指定日付以前）
 * "findByClass" ユーザIDから問い合わせ情報の検索(件名指定)
 * "findByClassAndAfterDate" ユーザIDから問い合わせ情報の検索（件名指定、指定日付以降）
 * "findByClassAndBeforeDate" ユーザIDから問い合わせ情報の検索（件名指定、指定日付以前）
 */

@Service
public class CustomerCallService {

	@Autowired
	CustomerCallRepository customerCallRepository;
	
	public CustomerCallForm create(CustomerCallForm customerCallForm) {
		CustomerCallBean customerCallBean = new CustomerCallBean();
		BeanUtils.copyProperties(customerCallForm, customerCallBean);
		customerCallRepository.save(customerCallBean);
		customerCallRepository.flush();
		return customerCallForm;
	}
	
//	public List<CustomerCallBean> findAllDataByCid(@Param("uId") String uId) {
//		return customerCallRepository.findAllDataByCid(uId);
//	}
	
	public String[][] findAllDataByCid(@Param("uId") String uId) {
		return customerCallRepository.findAllDataByCid(uId);
	}
	
	public String[][] findByAfterDate(@Param("uId") String uId, @Param("date") String date) {
		return customerCallRepository.findByAfterDate(uId, date);
	}
	
	public String[][] findByBeforeDate(@Param("uId") String uId, @Param("date") String date) {
		return customerCallRepository.findByBeforeDate(uId, date);
	}
	
	public String[][] findByClass(@Param("uId") String uId, @Param("classId") String classId) {
		return customerCallRepository.findByClass(uId, classId);
	}
	
	public String[][] findByClassAndAfterDate(@Param("uId") String uId, @Param("classId") String classId, @Param("date") String date) {
		return customerCallRepository.findByClassAndAfterDate(uId, classId, date);
	}
	
	public String[][] findByClassAndBeforeDate(@Param("uId") String uId, @Param("classId") String classId, @Param("date") String date) {
		return customerCallRepository.findByClassAndBeforeDate(uId, classId, date);
	}
}
