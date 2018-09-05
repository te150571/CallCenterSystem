package jp.te4a.zoo.spring.boot.CallCenterSystem.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 顧客情報データベースBean
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_user")
public class CustomerBean {

	// 顧客ID（最大数で自動割り振り）
	@Id
	private String id;
	
	// 苗字（かな）
	private String lastname_kana;
		
	// 名前（かな）
	private String firstname_kana;
	
	// 苗字（漢字）
	private String lastname;
	
	// 名前（漢字）
	private String firstname;
	
	// 郵便番号
	private String addresscode;
	
	// 住所
	private String address;
	
	// 電話番号
	private String tel;
	
	// 誕生日
	private String birth;
}
