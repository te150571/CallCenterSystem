package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.EmployeeBean;

/*
 * 社員データベースRepository
 * 使わない 削除予定
 */

public interface EmployeeRepository extends JpaRepository<EmployeeBean, String> {

}
