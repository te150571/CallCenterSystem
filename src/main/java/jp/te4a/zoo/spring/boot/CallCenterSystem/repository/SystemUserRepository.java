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
	
	/*
	 * ユーザIDでパスワードを取得
	 * uId	: ユーザID
	 * 
	 * 戻り値	: エンコード済みパスワード(String)
	 */
	@Query("select user.password from SystemUserBean user where user.id = :uId")
	public String getPassword(@Param("uId") String uId);
	
	/*
	 * ユーザのパスワードを変更する
	 * uId		: ユーザID
	 * newpass	: 新しいエンコード済みパスワード
	 * 
	 * 戻り値	:　なし
	 */
	@Modifying
	@Query("update SystemUserBean user set user.password = :newpass where user.id = :uId")
	public void updatePassword(@Param("uId") String uId, @Param("newpass") String newpass);
	
	/*
	 * 初回アクセスフラグを0にする
	 * uId	: ユーザID
	 * 
	 * 戻り値	:　なし
	 */
	@Modifying
	@Query("update SystemUserBean user set user.firstAccFlag = 0 where user.id = :uId")
	public void updateFirstAccFlag(@Param("uId") String uId);
}
