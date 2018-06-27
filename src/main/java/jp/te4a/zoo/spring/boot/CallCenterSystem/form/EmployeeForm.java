package jp.te4a.zoo.spring.boot.CallCenterSystem.form;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 社員データベースForm
 */

@Data
@NoArgsConstructor
public class EmployeeForm {

	// 社員番号（ID）
	@NotNull
	private String id;
	
	// 社員名
	private String name;
	
	// メールアドレス
	private String mail;
	
	// 電話番号
	private String tel;
}
