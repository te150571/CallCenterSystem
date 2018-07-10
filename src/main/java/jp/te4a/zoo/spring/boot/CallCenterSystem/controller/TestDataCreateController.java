package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.ClassBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerCallBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.SystemUserBean;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.ClassForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.CustomerCallForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.CustomerForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.form.SystemUserForm;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.ClassService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.CustomerCallService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.CustomerService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.SystemUserService;

@Controller
@RequestMapping("test")
public class TestDataCreateController {

	@Autowired
	SystemUserService userService;
	
	@Autowired
	ClassService classService;

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerCallService customerCallService;

	// テスト用データ作成
	@RequestMapping
	String testDataCreate() {
		System.out.println("DEBUG:TestDataCreate. Start.");
		System.out.println("DEBUG:SystemUserCreate. Start.");
		// テスト用システムユーザ登録
		SystemUserBean userBean1 = new SystemUserBean("111111", "password", "TestUser1", 0);
		SystemUserBean userBean2 = new SystemUserBean("222222", "password", "TestUser2", 1);
		SystemUserForm userForm = new SystemUserForm();
		BeanUtils.copyProperties(userBean1, userForm);
		userService.create(userForm);
		BeanUtils.copyProperties(userBean2, userForm);
		userService.create(userForm);
		System.out.println("DEBUG:SystemUserCreate. End.");
		System.out.println("DEBUG:CustomerCreate. Start.");
		// テスト用顧客データ登録
		CustomerBean customerBean1 = new CustomerBean("11111111", "てすと", "ゆーざ1", "テスト", "ユーザ1", "住所1", "電話1", "1990/01/01");
		CustomerBean customerBean2 = new CustomerBean("11111112", "てすと", "ゆーざ2", "テスト", "ユーザ2", "住所2", "電話2", "1995/01/01");
		CustomerForm customerForm = new CustomerForm();
		BeanUtils.copyProperties(customerBean1, customerForm);
		customerService.create(customerForm);
		BeanUtils.copyProperties(customerBean2, customerForm);
		customerService.create(customerForm);
		System.out.println("DEBUG:CustomerCreate. End.");
		System.out.println("DEBUG:CustomerCallCreate. Start.");
		System.out.println("DEBUG:ClassCreate. Start.");
		// テスト用問い合わせ情報登録
		// 分類テーブル設定
		String[] clsName = {"依頼", "問い合わせ", "サポート", "クレーム", "その他"};
		for(int i=0; i<clsName.length; i++) {
			ClassBean classBean = new ClassBean("00" + (i + 1), clsName[i]);
			ClassForm classForm = new ClassForm();
			BeanUtils.copyProperties(classBean, classForm);
			classService.create(classForm);
		}
		System.out.println("DEBUG:ClassCreate. End.");
		String[] cls = {"002", "002", "001", "003", "004", "005", "001"};
		String[] contents = {"問い合わせ1", "問い合わせ2", "問い合わせ3", "問い合わせ4", "問い合わせ5", "問い合わせ6", "問い合わせ7"};
		String[] date = {"2016/01/02", "2016/03/03", "2017/01/02", "2017/07/02", "2018/05/02", "2018/06/02", "2018/06/10"};
		String[] cId = {"11111111", "11111112", "11111112", "11111111", "11111112", "11111112", "11111111"};
		for(int i=0; i<cls.length; i++) {
			CustomerCallBean customerCallBean = new CustomerCallBean();
			customerCallBean.setId("0000000" + i);
			customerCallBean.setClass_id(cls[i]);
			customerCallBean.setContents(contents[i]);
			customerCallBean.setDate(date[i]);
			customerCallBean.setC_id(cId[i]);
			CustomerCallForm customerCallForm = new CustomerCallForm();
			BeanUtils.copyProperties(customerCallBean, customerCallForm);
			customerCallService.create(customerCallForm);
		}
		System.out.println("DEBUG:CustomerCallCreate. End.");
		System.out.println("DEBUG:TestDataCreate. End.");
		
		return "redirect:/";
	}
}
