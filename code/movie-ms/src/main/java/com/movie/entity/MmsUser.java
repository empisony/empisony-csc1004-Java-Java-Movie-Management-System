package com.movie.entity;

/**
 * 
 * @desc 用户信息
 *
 */
public class MmsUser {

	private String userCode; // 用户账号
	private String userName; // 用户姓名
	private String nickName; // 昵称
	private String age; // 年龄
	private String sex; // 性别
	private String address; // 住址
	private String password; // 密码
	private String isAdmin; // 是否管理员
	
	public MmsUser() {}
	
	public MmsUser(String userCode, String userName, String nickName, String age, String sex, String address,
			String password, String isAdmin) {
		super();
		this.userCode = userCode;
		this.userName = userName;
		this.nickName = nickName;
		this.age = age;
		this.sex = sex;
		this.address = address;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
}
