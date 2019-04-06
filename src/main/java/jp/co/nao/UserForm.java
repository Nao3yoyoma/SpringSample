package jp.co.nao;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "{userForm.name}")
	@Size(min = 1, max = 20, message = "{userForm.name}")
	private String name;

	@NotNull
	@Size(min = 1, max = 50)
	private String email;

	@NotNull
	@Min(0)
	@Max(200)
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}