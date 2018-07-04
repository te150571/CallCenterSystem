package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerBean;

/*
 * 顧客情報データベースRepository
 * "SearchCustomerId" 入力された「苗字」「名前」「電話番号」「住所」をもとに顧客IDを検索
 */

public interface CustomerRepository extends JpaRepository<CustomerBean, String> {

	@Query("select customer.id from CustomerBean customer where customer.lastname = :uLastName and customer.firstname = :uFirstName and customer.tel = :uTel and customer.address = :uAdd order by customer.id asc")
	public String SearchCustomerId(@Param("uLastName") String uLastName, @Param("uFirstName") String uFirstName, @Param("uTel") String uTel, @Param("uAdd") String uAdd);
}
