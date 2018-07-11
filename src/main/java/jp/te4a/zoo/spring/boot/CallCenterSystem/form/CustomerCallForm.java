package jp.te4a.zoo.spring.boot.CallCenterSystem.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 顧客問い合わせ情報データベースForm
 */

@Data
@NoArgsConstructor
public class CustomerCallForm {

	// 問い合わせID（自動割り振り）
	@NotNull
	@Size(max=12)
	private String id;

	// 件名（分類）
	@Size(max=3)
	private String class_id;

	// 本文
	private String contents;

	// 日付
	private String date;
	
	// 対応する顧客ID(外部参照)
	private String c_id;
}
