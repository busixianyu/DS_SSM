package cn.ds.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DsUser {
	
	private Integer id;
	@NotEmpty(message = "姓名不能为空！")
	private String name;
	@NotNull(message = "年龄不能为空！")
	@Max(value = 200,message = "年龄最大不能超过200岁！")
	@Min(value=1,message = "年龄最小不能小于1岁！")
	private Integer age;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "DsUser{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
