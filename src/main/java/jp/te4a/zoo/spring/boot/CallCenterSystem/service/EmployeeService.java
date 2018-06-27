package jp.te4a.zoo.spring.boot.CallCenterSystem.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.EmployeeBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.EmployeeForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.repository.EmployeeRepository;

/*
 * 社員データベースService
 * "create" ユーザ登録（テスト用）
 */

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public EmployeeForm create(EmployeeForm employeeForm) {
		
		EmployeeBean employeeBean = new EmployeeBean();
		BeanUtils.copyProperties(employeeForm, employeeBean);
		employeeRepository.save(employeeBean);
		employeeRepository.flush();
		return employeeForm;
	}
}
