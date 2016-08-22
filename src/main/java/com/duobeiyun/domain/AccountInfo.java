package com.duobeiyun.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dapeng on 16/8/18.
 */
@Entity
@Table(name = "account_info")
public class AccountInfo implements Serializable {

	private static final long serialVersionUID = -5311140719509185736L;

	@Id
	@GeneratedValue
	private Long id;

	private String username;

	private String passwd;

	private Date createTime;

	@Column(columnDefinition = "tinyint(4) default 0")
	private int role = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
