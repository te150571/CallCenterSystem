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
 * 
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
	
	// IPアドレス
	private String ip;
	
	// 日付
	private String date;
	
	// 入力されたユーザ名
	private String userId;
	
	// アクセス結果
	private int accResult;
}
