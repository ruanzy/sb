package com.rz.sb.entity;

import java.io.Serializable;

public class LoginUser implements Serializable {

	private static final long serialVersionUID = 8809101560720973267L;

	private String account;

	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}