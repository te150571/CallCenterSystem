package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerCallBean;

/*
 * 顧客問い合わせ情報データベースRepository
 * "findAllDataByCid" ユーザIDから問い合わせ情報の検索
 * "SearchDataAllFromDateAfter" ユーザIDから問い合わせ情報の検索（件名指定なし、指定日付以降）
 * "SearchDataAllFromDateBefore" ユーザIDから問い合わせ情報の検索（件名指定なし、指定日付以前）
 * "SearchDataAllFromClass" ユーザIDから問い合わせ情報の検索(件名指定)
 * "SearchDataAllFromClassAndDateAfter" ユーザIDから問い合わせ情報の検索（件名指定、指定日付以降）
 * "SearchDataAllFromClassAndDateBefore" ユーザIDから問い合わせ情報の検索（件名指定、指定日付以前）
 * "SearchDataOne" IDから一件だけ抽出
 */

public interface CustomerCallRepository extends JpaRepository<CustomerCallBean, String> {
	
//	@Query("from CustomerCallBean customer where customer.c_id = :uId order by id asc")
//	public List<CustomerCallBean> findAllDataByCid(@Param("uId") String uId);
	
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.id = :id",
			nativeQuery = true)
	public String[][] findDataById(@Param("id") String id);
	
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.c_id = :uId"
			+ " order by id asc",
			nativeQuery = true)
	public String[][] findAllDataByCid(@Param("uId") String uId);
	
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.c_id = :uId and customer.date >= :date"
			+ " order by id asc",
			nativeQuery = true)
	public String[][] findByAfterDate(@Param("uId") String uId, @Param("date") String date);
	
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.c_id = :uId and customer.date <= :date"
			+ " order by id asc",
			nativeQuery = true)
	public String[][] findByBeforeDate(@Param("uId") String uId, @Param("date") String date);
	
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.c_id = :uId and customer.class_id <= :classId"
			+ " order by id asc",
			nativeQuery = true)
	public String[][] findByClass(@Param("uId") String uId, @Param("classId") String classId);
	
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.c_id = :uId and customer.class_id <= :classId and customer.date >= :date"
			+ " order by id asc",
			nativeQuery = true)
	public String[][] findByClassAndAfterDate(@Param("uId") String uId, @Param("classId") String classId, @Param("date") String date);
	
	@Query(value = "select customer.id as id, cls.name as clsName, customer.contents as contents, customer.date as date"
			+ " from t_call customer left join t_callclass cls on customer.class_id = cls.id"
			+ " where customer.c_id = :uId and customer.class_id <= :classId and customer.date <= :date"
			+ " order by id asc",
			nativeQuery = true)
	public String[][] findByClassAndBeforeDate(@Param("uId") String uId, @Param("classId") String classId, @Param("date") String date);
}
