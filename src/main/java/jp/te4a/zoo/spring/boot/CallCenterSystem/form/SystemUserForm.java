package jp.te4a.zoo.spring.boot.CallCenterSystem.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * コールセンター顧客管理システムログインユーザデータベースForm
 */

@Data
@NoArgsConstructor
public class SystemUserForm {
	
	// 社員番号（ID）
	@NotNull
	@Size(min=6, max=6)
	private String username;
	
	// パスワード
	@Size(min=6, max=12)
	private String password;
	
	// 初回ログインフラグ
	private int firstAccFlag;
}
