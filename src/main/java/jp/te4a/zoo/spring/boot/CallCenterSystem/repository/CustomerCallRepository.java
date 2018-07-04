package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.te4a.zoo.spring.boot.CallCenterSystem.bean.CustomerCallBean;

/*
 * 顧客問い合わせ情報データベースRepository
 * "SearchDataAll" ユーザIDから問い合わせ情報の検索
 * "SearchDataAllFromDateAfter" ユーザIDから問い合わせ情報の検索（件名指定なし、指定日付以降）
 * "SearchDataAllFromDateBefore" ユーザIDから問い合わせ情報の検索（件名指定なし、指定日付以前）
 * "SearchDataAllFromClass" ユーザIDから問い合わせ情報の検索(件名指定)
 * "SearchDataAllFromClassAndDateAfter" ユーザIDから問い合わせ情報の検索（件名指定、指定日付以降）
 * "SearchDataAllFromClassAndDateBefore" ユーザIDから問い合わせ情報の検索（件名指定、指定日付以前）
 * "SearchDataOne" IDから一件だけ抽出
 */

public interface CustomerCallRepository extends JpaRepository<CustomerCallBean, String> {
	
	@Query("select customer.id, customer.class_id, customer.content, customer.date from CustomerCallBean where customer.c_id = :uId order by id asc")
	public List<String> SearchDataAll(@Param("uId") String uId);
	
	@Query("select customer.id, customer.class_id, customer.content, customer.date from CustomerCallBean where customer.c_id = :uId and customer.date >= :date order by id asc")
	public List<String> SearchDataAllFromDateAfter(@Param("uId") String uId, @Param("date") String date);
	
	@Query("select customer.id, customer.class_id, customer.content, customer.date from CustomerCallBean where customer.c_id = :uId and customer.date <= :date order by id asc")
	public List<String> SearchDataAllFromDateBefore(@Param("uId") String uId, @Param("date") String date);
	
	@Query("select customer.id, customer.class_id, customer.content, customer.date from CustomerCallBean where customer.c_id = :uId and customer.class_id <= :classId order by id asc")
	public List<String> SearchDataAllFromClass(@Param("uId") String uId, @Param("classId") String classId);
	
	@Query("select customer.id, customer.class_id, customer.content, customer.date from CustomerCallBean where customer.c_id = :uId and customer.class_id <= :classId and customer.date >= :date order by id asc")
	public List<String> SearchDataAllFromClassAndDateAfter(@Param("uId") String uId, @Param("classId") String classId, @Param("date") String date);
	
	@Query("select customer.id, customer.class_id, customer.content, customer.date from CustomerCallBean where customer.c_id = :uId and customer.class_id <= :classId and customer.date <= :date order by id asc")
	public List<String> SearchDataAllFromClassAndDateBefore(@Param("uId") String uId, @Param("classId") String classId, @Param("date") String date);
	
	@Query("select customer.id, customer.class_id, customer.content, customer.date from CustomerCallBean where customer.id = :cId order by id asc")
	public List<String> SearchDataOne(@Param("cId") String cId);
}
