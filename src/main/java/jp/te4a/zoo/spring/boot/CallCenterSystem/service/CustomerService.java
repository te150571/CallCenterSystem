package jp.te4a.zoo.spring.boot.CallCenterSystem.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.CustomerForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.repository.CustomerRepository;

/*
 * 顧客情報データベースService
 * "create" 新規顧客登録
 * "SearchCustomerId" 入力された「苗字」「名前」「電話番号」「住所」をもとに顧客IDを検索
 * "findNameByUid" ユーザIDから顧客名を検索
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
	
	public String searchCustomerId(String uLastName, String uFirstName, String uTel, String uAdd) {
		return customerRepository.findCustomerId(uLastName, uFirstName, uTel, uAdd);
	}
	
	public String findNameByUid(@Param("uId") String uId) {
		return customerRepository.findNameByUid(uId);
	}
}
