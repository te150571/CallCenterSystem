package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.ClassBean;

/*
 * 件名（分類）データベースRepository
 */

public interface ClassRepository extends JpaRepository<ClassBean, String> {

	@Query("select cls.id from ClassBean cls where cls.name = :inName")
	String findIdByName(@Param("inName") String inName);
}
