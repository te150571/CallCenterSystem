package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.SystemUserBean;

/*
 * コールセンター顧客管理システムログインユーザデータベースRepository
 */

public interface SystemUserRepository extends JpaRepository<SystemUserBean, String> {

}
