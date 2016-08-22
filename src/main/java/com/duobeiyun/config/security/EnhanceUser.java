package com.duobeiyun.config.security;

import java.io.Serializable;

public class EnhanceUser implements Serializable {

	private static final long serialVersionUID = -7336389088639221141L;

	private Long accountId;

	private String username;

	public EnhanceUser(){
	}

	public EnhanceUser(Long accountId, String username){
		this.accountId = accountId;
		this.username = username;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
