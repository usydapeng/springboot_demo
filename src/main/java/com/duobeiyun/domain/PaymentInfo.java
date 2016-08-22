package com.duobeiyun.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dapeng on 16/8/18.
 */
@Entity
@Table(name = "payment_info")
public class PaymentInfo implements Serializable {

	private static final long serialVersionUID = 4797550724341909240L;

	@Id
	@GeneratedValue
	private Long id;

	private String outTradeNo;

	private Long accountId;

	@Column(columnDefinition = "tinyint(4) default 0")
	private int status = 0;

	private double totalFee = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
}
