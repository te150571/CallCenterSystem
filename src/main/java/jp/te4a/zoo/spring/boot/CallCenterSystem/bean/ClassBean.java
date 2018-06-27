package jp.te4a.zoo.spring.boot.CallCenterSystem.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 件名（分類）データベースBean
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="class")
public class ClassBean {
	
	@Id
	// ID
	private String id;
}
