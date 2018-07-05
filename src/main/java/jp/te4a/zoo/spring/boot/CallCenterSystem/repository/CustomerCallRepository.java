package jp.te4a.zoo.spring.boot.CallCenterSystem.repository;

import java.util.List;
import java.util.Optional;

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
	
	@Query("select customer.id, customer.class_id, customer.contents, customer.date from CustomerCallBean customer where customer.c_id = :uId order by id asc")
	public Optional<CustomerCallBean> searchDataAll(@Param("uId") String uId);
	
	@Query("select customer.id, customer.class_id, customer.contents, customer.date from CustomerCallBean customer where customer.c_id = :uId and customer.date >= :date order by customer.id asc")
	public Optional<CustomerCallBean> searchDataAllFromDateAfter(@Param("uId") String uId, @Param("date") String date);
	
	@Query("select customer.id, customer.class_id, customer.contents, customer.date from CustomerCallBean customer where customer.c_id = :uId and customer.date <= :date order by customer.id asc")
	public Optional<CustomerCallBean> searchDataAllFromDateBefore(@Param("uId") String uId, @Param("date") String date);
	
	@Query("select customer.id, customer.class_id, customer.contents, customer.date from CustomerCallBean customer where customer.c_id = :uId and customer.class_id <= :classId order by customer.id asc")
	public Optional<CustomerCallBean> searchDataAllFromClass(@Param("uId") String uId, @Param("classId") String classId);
	
	@Query("select customer.id, customer.class_id, customer.contents, customer.date from CustomerCallBean customer where customer.c_id = :uId and customer.class_id <= :classId and customer.date >= :date order by customer.id asc")
	public Optional<CustomerCallBean> searchDataAllFromClassAndDateAfter(@Param("uId") String uId, @Param("classId") String classId, @Param("date") String date);
	
	@Query("select customer.id, customer.class_id, customer.contents, customer.date from CustomerCallBean customer where customer.c_id = :uId and customer.class_id <= :classId and customer.date <= :date order by customer.id asc")
	public Optional<CustomerCallBean> searchDataAllFromClassAndDateBefore(@Param("uId") String uId, @Param("classId") String classId, @Param("date") String date);
}
