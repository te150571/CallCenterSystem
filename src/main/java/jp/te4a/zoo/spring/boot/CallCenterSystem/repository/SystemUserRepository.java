package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.SystemUserBean;

/*
 * コールセンター顧客管理システムログインユーザデータベースRepository
 * "updatePassword" ユーザのパスワードを変更する
 * "updateFirstFlag" 初回ログインフラグを更新する
 */

@Transactional
public interface SystemUserRepository extends JpaRepository<SystemUserBean, String> {
	
	@Query("select user.password from SystemUserBean user where user.id = :uid")
	public String getPassword(@Param("uid") String uid);
	
	@Modifying
	@Query("update SystemUserBean user set user.password = :newpass where user.id = :uid")
	public void updatePassword(@Param("uid") String uid, @Param("newpass") String pass);
	
	@Modifying
	@Query("update SystemUserBean user set user.firstAccFlag = 0 where user.id = :uid")
	public void updateFirstAccFlag(@Param("uid") String uid);
}
