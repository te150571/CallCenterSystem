package jp.te4a.zoo.spring.boot.CallCenterSystem.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.CustomerForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.repository.CustomerRepository;

/*
 * 顧客情報データベースService
 * "create" 新規顧客登録
 */

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public CustomerForm create(CustomerForm customerForm) {
		CustomerBean customerBean = new CustomerBean();
		BeanUtils.copyProperties(customerForm, customerBean);
		customerRepository.save(customerBean);
		customerRepository.flush();
		return customerForm;
	}
}
