package jp.te4a.zoo.spring.boot.CallCenterSystem.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

/*
 * コールセンター顧客管理システムログインユーザデータベースBean
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="callsys_user")
public class SystemUserBean {

	// 社員番号（ID）
	@Id
	private String username;

	// パスワード
	@JsonIgnore
	private String password;

	// 社員名
	private String name;

	// 初回ログインフラグ
	private int firstAccFlag;
}
