package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.ClassBean;

/*
 * 件名（分類）データベースRepository
 */

public interface ClassRepository extends JpaRepository<ClassBean, String> {

}
