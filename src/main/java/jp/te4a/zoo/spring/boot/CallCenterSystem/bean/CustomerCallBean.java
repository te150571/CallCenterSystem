package jp.te4a.zoo.spring.boot.CallCenterSystem.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 顧客問い合わせ情報データベースBean
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_call")
public class CustomerCallBean {

	// 顧客ID（最大数で自動割り振り）
	@Id
	private String id;
	
	// 件名（分類）
	private String class_id;
	
	// 本文
	private String contents;
	
	// 日付
	private String date;
	
	// 対応する顧客ID(外部参照)
	private String c_id;
}
