package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.LockedIdBean;

public interface LockedIdRepository extends JpaRepository<LockedIdBean, String> {
	
	@Query("select lock.lockedId from LockedIdBean lock where lock.id = (select max(lock2.id) from LockedIdBean lock2 where lock2.lockedId = :uId)")
	public String lockedCheckByUId(@Param("uId") String uId);
}
