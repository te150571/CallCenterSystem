package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.AccessLogBean;

/*
 * アクセスログデータベースRepository
 */

public interface AccessLogRepository extends JpaRepository<AccessLogBean, String> {

	@Query("select max(id) from AccessLogBean acc where acc.ip = :ip")
	public String findByIpLast(@Param("ip") String ip);
	
	@Query("select count(*) from AccessLogBean acc where id = (select acc2.id from AccessLogBean acc2 where acc2.ip = :ip and rownum <= 5 order by id desc) and acc.accResult = 0")
	public int countLoginMiss(@Param("ip") String ip);
}
