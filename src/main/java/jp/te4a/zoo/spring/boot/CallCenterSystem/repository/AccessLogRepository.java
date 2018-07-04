package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.AccessLogBean;

/*
 * アクセスログデータベースRepository
 * "findByIdLast" 対象のユーザIDに対応する直近のログのIDを返す（ログイン成功フラグ更新用）
 * "countLoginMiss" 対象ユーザIDでの直近5回のログイン成否を返す（ログインロック用）
 * "updateAccResult" 対象の最終ログインログの成否フラグを更新する
 */

@Transactional
public interface AccessLogRepository extends JpaRepository<AccessLogBean, String> {

	@Query("select max(id) from AccessLogBean acc where acc.userId = :uId")
	public int findByUIdLast(@Param("uId") String uId);
	
	@Query("select count(*) from AccessLogBean acc where id in (select acc2.id from AccessLogBean acc2 where acc2.userId = :uId and rownum <= 5 order by id desc) and acc.accResult = 0")
	public int countLoginMiss(@Param("uId") String userId);
	
	@Modifying
	@Query("update AccessLogBean acc set acc.accResult = 1 where acc.id = :logid")
	public void updateAccResult(@Param("logid") Integer id);
}
