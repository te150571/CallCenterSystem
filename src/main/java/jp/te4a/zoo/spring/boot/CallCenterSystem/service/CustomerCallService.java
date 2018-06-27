package jp.te4a.zoo.spring.boot.CallCenterSystem.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerCallBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.CustomerCallForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.repository.CustomerCallRepository;

/*
 * 顧客問い合わせデータベースService
 * "create" 新規問い合わせ登録
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
}
