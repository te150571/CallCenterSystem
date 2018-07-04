package jp.te4a.zoo.spring.boot.CallCenterSystem.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * アクセスログデータベースBean
 */

@Data
@NoArgsConstructor
@Entity
@Table(name="accesslog")
public class AccessLogBean {

	// ID（連番）
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// IPAddress
	private String ip;
	
	// MacAddress
	private String mac;

	// 入力されたユーザ名
	private String userId;

	// 日付
	private String date;

	// アクセス結果
	private int accResult;
}