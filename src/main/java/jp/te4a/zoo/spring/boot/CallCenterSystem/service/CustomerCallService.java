package jp.te4a.zoo.spring.boot.CallCenterSystem.service;

import java.util.Optional;

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
 * "SearchDataAll" ユーザIDから問い合わせ情報の検索
 * "SearchDataAllFromDateAfter" ユーザIDから問い合わせ情報の検索（件名指定なし、指定日付以降）
 * "SearchDataAllFromDateBefore" ユーザIDから問い合わせ情報の検索（件名指定なし、指定日付以前）
 * "SearchDataAllFromClass" ユーザIDから問い合わせ情報の検索(件名指定)
 * "SearchDataAllFromClassAndDateAfter" ユーザIDから問い合わせ情報の検索（件名指定、指定日付以降）
 * "SearchDataAllFromClassAndDateBefore" ユーザIDから問い合わせ情報の検索（件名指定、指定日付以前）
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
	
	public Optional<CustomerCallBean> searchDataAll(@Param("uId") String uId) {
		return customerCallRepository.searchDataAll(uId);
	}
	
	public Optional<CustomerCallBean> searchDataAllFromDateAfter(@Param("uId") String uId, @Param("date") String date) {
		return customerCallRepository.searchDataAllFromDateAfter(uId, date);
	}
	
	public Optional<CustomerCallBean> searchDataAllFromDateBefore(@Param("uId") String uId, @Param("date") String date) {
		return customerCallRepository.searchDataAllFromDateBefore(uId, date);
	}
	
	public Optional<CustomerCallBean> searchDataAllFromClass(@Param("uId") String uId, @Param("classId") String classId) {
		return customerCallRepository.searchDataAllFromClass(uId, classId);
	}
	
	public Optional<CustomerCallBean> searchDataAllFromClassAndDateAfter(@Param("uId") String uId, @Param("classId") String classId, @Param("date") String date) {
		return customerCallRepository.searchDataAllFromClassAndDateAfter(uId, classId, date);
	}
	
	public Optional<CustomerCallBean> searchDataAllFromClassAndDateBefore(@Param("uId") String uId, @Param("classId") String classId, @Param("date") String date) {
		return customerCallRepository.searchDataAllFromClassAndDateBefore(uId, classId, date);
	}
}
