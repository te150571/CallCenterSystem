package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerCallBean;

/*
 * 顧客問い合わせ情報データベースRepository
 * "findAllDataByCid" 顧客IDから問い合わせ情報の検索
 * "SearchDataAllFromDateAfter" 顧客IDから問い合わせ情報の検索（件名指定なし、指定日付以降）
 * "SearchDataAllFromDateBefore" 顧客IDから問い合わせ情報の検索（件名指定なし、指定日付以前）
 * "SearchDataAllFromClass" 顧客IDから問い合わせ情報の検索(件名指定)
 * "SearchDataAllFromClassAndDateAfter" 顧客IDから問い合わせ情報の検索（件名指定、指定日付以降）
 * "SearchDataAllFromClassAndDateBefore" 顧客IDから問い合わせ情報の検索（件名指定、指定日付以前）
 */

public interface CustomerCallRepository extends JpaRepository<CustomerCallBean, String> {
	
	/*
	 *  
	 */
	@Query(value = "select max(id) from CustomerCallBean")
	public String getMaxId();
	
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.id = :id",
			nativeQuery = true)
	public String[][] findDataById(@Param("id") String id);
	
	/*
	 * 顧客IDから問い合わせ情報の検索
	 * cId : 顧客ID
	 * 
	 * 戻り値
	 * string[][]
	 * 		string[x][0] : 顧客ID
	 * 		string[x][1] :　分類名
	 * 		string[x][2] : 問い合わせ本文
	 * 		string[x][3] : 問い合わせ日付
	 */
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.c_id = :cId"
			+ " order by id asc",
			nativeQuery = true)
	public String[][] findAllDataByCid(@Param("cId") String cId);
	
	/*
	 * 顧客IDから問い合わせ情報の検索（件名指定なし、指定日付以降）
	 * cId : 顧客ID
	 * date : 日付(yyyy/MM/dd)
	 * 
	 * 戻り値
	 * string[][]
	 * 		string[x][0] : 顧客ID
	 * 		string[x][1] :　分類名
	 * 		string[x][2] : 問い合わせ本文
	 * 		string[x][3] : 問い合わせ日付
	 */
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.c_id = :cId and customer.date >= :date"
			+ " order by id asc",
			nativeQuery = true)
	public String[][] findByAfterDate(@Param("cId") String cId, @Param("date") String date);
	
	/*
	 * 顧客IDから問い合わせ情報の検索（件名指定なし、指定日付以前）
	 * cId : 顧客ID
	 * date : 日付(yyyy/MM/dd)
	 * 
	 * 戻り値
	 * string[][]
	 * 		string[x][0] : 顧客ID
	 * 		string[x][1] :　分類名
	 * 		string[x][2] : 問い合わせ本文
	 * 		string[x][3] : 問い合わせ日付
	 */
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.c_id = :cId and customer.date <= :date"
			+ " order by id asc",
			nativeQuery = true)
	public String[][] findByBeforeDate(@Param("cId") String cId, @Param("date") String date);
	
	/*
	 * 顧客IDから問い合わせ情報の検索(件名指定)
	 * cId : 顧客ID
	 * classId : 件名ID
	 * 
	 * 戻り値
	 * string[][]
	 * 		string[x][0] : 顧客ID
	 * 		string[x][1] :　分類名
	 * 		string[x][2] : 問い合わせ本文
	 * 		string[x][3] : 問い合わせ日付
	 */
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.c_id = :cId and customer.class_id <= :classId"
			+ " order by id asc",
			nativeQuery = true)
	public String[][] findByClass(@Param("cId") String cId, @Param("classId") String classId);
	
	/*
	 * 顧客IDから問い合わせ情報の検索（件名指定、指定日付以降）
	 * cId : 顧客ID
	 * classId : 件名ID
	 * date : 日付(yyyy/MM/dd)
	 * 
	 * 戻り値
	 * string[][]
	 * 		string[x][0] : 顧客ID
	 * 		string[x][1] :　分類名
	 * 		string[x][2] : 問い合わせ本文
	 * 		string[x][3] : 問い合わせ日付
	 */
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.c_id = :cId and customer.class_id <= :classId and customer.date >= :date"
			+ " order by id asc",
			nativeQuery = true)
	public String[][] findByClassAndAfterDate(@Param("cId") String cId, @Param("classId") String classId, @Param("date") String date);
	
	/*
	 * 顧客IDから問い合わせ情報の検索（件名指定、指定日付以前）
	 * cId : 顧客ID
	 * classId : 件名ID
	 * date : 日付(yyyy/MM/dd)
	 * 
	 * 戻り値
	 * string[][]
	 * 		string[x][0] : 顧客ID
	 * 		string[x][1] :　分類名
	 * 		string[x][2] : 問い合わせ本文
	 * 		string[x][3] : 問い合わせ日付
	 */
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.c_id = :cId and customer.class_id <= :classId and customer.date <= :date"
			+ " order by id asc",
			nativeQuery = true)
	public String[][] findByClassAndBeforeDate(@Param("cId") String cId, @Param("classId") String classId, @Param("date") String date);
}
