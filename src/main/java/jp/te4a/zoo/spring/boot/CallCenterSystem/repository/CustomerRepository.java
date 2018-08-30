package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerBean;

/*
 * 顧客情報データベースRepository
 * "SearchCustomerId" 入力された「苗字」「名前」「電話番号」「住所」をもとに顧客IDを検索
 * "findNameByUid" 顧客IDから顧客名を検索
 */

public interface CustomerRepository extends JpaRepository<CustomerBean, String> {

	/*
	 * 入力された「苗字」「名前」「電話番号」「住所」をもとに顧客IDを検索
	 * uLastName 	: 顧客の名字
	 * uFirstName   : 顧客の名前
	 * uTel			: 顧客の電話番号
	 * uAdd			: 顧客の住所
	 * 
	 * 戻り値			: 顧客ID(String)
	 */
	@Query("select customer.id from CustomerBean customer where customer.lastname = :uLastName and customer.firstname = :uFirstName and customer.tel = :uTel and customer.address = :uAdd order by customer.id asc")
	public String findCustomerId(@Param("uLastName") String uLastName, @Param("uFirstName") String uFirstName, @Param("uTel") String uTel, @Param("uAdd") String uAdd);
	
	/*
	 *  顧客IDから顧客名を検索
	 *  uId		: 顧客ID
	 *  戻り値	: 顧客の名前(名字、名前結合済み)
	 */
	@Query("select concat(customer.lastname, customer.firstname) from CustomerBean customer where customer.id = :uId")
	public String findNameByUid(@Param("uId") String uId);
	
	/*
	 * 顧客IDの最大値を取得する
	 * 戻り値		: 顧客IDの最大値
	 */
	@Query("select max(id) from CustomerBean")
	public String getMaxId();
}
