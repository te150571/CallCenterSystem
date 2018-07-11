package jp.te4a.zoo.spring.boot.CallCenterSystem.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="t_callclass")
public class ClassBean {
	
	@Id
	// ID
	private String id;
	
	// 分類名
	private String name;
	
	@OneToMany(mappedBy = "classBean")
	private List<CustomerCallBean> customerCallBean;
}
