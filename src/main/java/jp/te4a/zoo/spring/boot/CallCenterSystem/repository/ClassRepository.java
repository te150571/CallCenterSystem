package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.ClassBean;

/*
 * 件名（分類）データベースRepository
 * "findIdByName" 分類名からIDを取得
 */

public interface ClassRepository extends JpaRepository<ClassBean, String> {

	/*
	 * 分類名からIDを取得
	 * inName : 検索する分類名
	 * 戻り値 : 分類ID 
	 */
	@Query("select cls.id from ClassBean cls where cls.name = :inName")
	String findIdByName(@Param("inName") String inName);
}
