package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.LockedIpAddressBean;

public interface LockedIpAddressRepository extends JpaRepository<LockedIpAddressBean, String> {
	
	@Query("select lock.locked from LockedIpAddressBean lock where lock.id = (select max(lock2.id) from LockedIpAddressBean lock2 where lock2.ip = :ip)")
	public String findByIp(@Param("ip") String ip);
}
