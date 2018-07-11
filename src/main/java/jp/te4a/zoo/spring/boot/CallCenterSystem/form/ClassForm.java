package jp.te4a.zoo.spring.boot.CallCenterSystem.form;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 件名（分類）データベースForm
 */

@Data
@NoArgsConstructor
public class ClassForm {
	
	// ID
	@Id
	@NotNull
	private String id;
	
	// 分類名
	private String name;
}
