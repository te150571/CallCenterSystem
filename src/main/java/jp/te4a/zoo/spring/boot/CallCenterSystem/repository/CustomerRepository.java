package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerBean;

/*
 * 顧客情報データベースRepository
 */

public interface CustomerRepository extends JpaRepository<CustomerBean, String> {

}
