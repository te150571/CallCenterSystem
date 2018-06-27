package jp.te4a.zoo.spring.boot.CallCenterSystem.form;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * アクセスログデータベースForm
 */

@Data
@NoArgsConstructor
public class AccessLogForm {
	
	// ID
	@NotNull
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
