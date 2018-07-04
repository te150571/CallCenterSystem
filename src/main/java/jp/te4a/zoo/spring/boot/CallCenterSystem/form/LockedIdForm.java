package jp.te4a.zoo.spring.boot.CallCenterSystem.form;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * ロック済みIPアドレスデータベースForm
 */

@Data
@NoArgsConstructor
public class LockedIdForm {

	// ID
	@NotNull
	private int id;

	// ロックされたID
	private String lockedId;

	// 日付
	private String date;
	
	// ロックフラグ
	private int locked;
}
