package entity;

import annotations.Column;
import annotations.Table;
import enums.StuctType;

@Table(name = "user")
public class User {
	
	@Column(length = "20", type = StuctType.STRING, fieldName = "user_name")
	private String userName;
	@Column(length = "20", type = StuctType.STRING, fieldName = "password")
	private String password;
	@Column(length = "11", type = StuctType.INTEGER, fieldName = "age")
	private int age;
	@Column(length = "20", type = StuctType.STRING, fieldName = "phone")
	private String phone;
	@Column(length = "15", type = StuctType.LONG,isPremaryKey = true, fieldName = "id")
	private Long id;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
