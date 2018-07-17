package jp.te4a.zoo.spring.boot.CallCenterSystem.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.ClassBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.ClassForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.repository.ClassRepository;



@Service
public class ClassService {
	
	@Autowired
	ClassRepository classRepository;
	
	public ClassForm create(ClassForm classForm) {
		ClassBean classBean = new ClassBean();
		BeanUtils.copyProperties(classForm, classBean);
		classRepository.save(classBean);
		classRepository.flush();
		return classForm;
	}
	
	public String findIdByName(String inName) {
		return classRepository.findIdByName(inName);
	}
}
