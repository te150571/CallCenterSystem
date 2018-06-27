package jp.te4a.zoo.spring.boot.CallCenterSystem.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 社員データベースBean
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="emp")
public class EmployeeBean {
	
	// 社員番号（ID）
	@Id
	private String id;
	
	// 社員名
	private String name;
	
	// メールアドレス
	private String mail;
	
	// 電話番号
	private String tel;
}
