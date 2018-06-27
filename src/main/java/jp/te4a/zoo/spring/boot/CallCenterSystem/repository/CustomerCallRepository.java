package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerCallBean;

/*
 * 顧客問い合わせ情報データベースRepository
 */

public interface CustomerCallRepository extends JpaRepository<CustomerCallBean, String> {

}
