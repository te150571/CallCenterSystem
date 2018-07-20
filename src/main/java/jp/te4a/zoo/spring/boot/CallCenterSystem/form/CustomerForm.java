package jp.te4a.zoo.spring.boot.CallCenterSystem.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 顧客情報データベースForm
 */

@Data
@NoArgsConstructor
public class CustomerForm {

	// 顧客ID（最大数で自動割り振り）
	@NotNull
	private String id;

	// 苗字（かな）
	@Size(max=20)
	private String lastname_kana;

	// 名前（かな）
	@Size(max=20)
	private String firstname_kana;

	// 苗字（漢字）
	@Size(max=20)
	private String lastname;

	// 名前（漢字）
	@Size(max=20)
	private String firstname;

	// 郵便番号
	private String addresscode;
	
	// 住所
	@Size(max=255)
	private String address;

	// 電話番号
	@Size(max=12)
	private String tel;

	// 誕生日
	private String birth;
}
