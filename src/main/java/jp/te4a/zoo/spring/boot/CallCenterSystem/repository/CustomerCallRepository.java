package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerCallBean;

/*
 * 顧客問い合わせ情報データベースRepository
 * "SearchData" 問い合わせ情報の検索
 */

public interface CustomerCallRepository extends JpaRepository<CustomerCallBean, String> {
	
	@Query("")
	public List<String> SearchData(@Param("uName") String uName, @Param("uTel") String uTel, @Param("uAdd") String uAdd);
}
