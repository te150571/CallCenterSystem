package jp.te4a.zoo.spring.boot.CallCenterSystem.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * ロック済みIPアドレスデータベースBean
 */

@Data
@NoArgsConstructor
@Entity
@Table(name="locked_ip")
public class LockedIpAddressBean {

	// ID（連番）
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// IPアドレス
	private String ip;

	// 日付
	private String date;

	// ロックフラグ
	private int locked;
}
