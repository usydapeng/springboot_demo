package com.duobeiyun.web.advice;

import com.duobeiyun.config.security.EnhanceSecurityUtils;
import com.duobeiyun.domain.AccountInfo;
import com.duobeiyun.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Created by dapeng on 16/8/18.
 */
@ControllerAdvice
public class UserInfoControllerAdvice {

	@Autowired
	private AccountService accountService;

	@ModelAttribute("customUser")
	public AccountInfo index(){
		Long accountId = EnhanceSecurityUtils.retrieveEnhanceUser().getAccountId();
		if(accountId < 0){
			return null;
		}
		return accountService.getById(accountId);
	}

	@ModelAttribute("domainType")
	public int domainType(@RequestHeader("Host") String host){

		if(host != null && host.contains("shequnke.com")){
			return 2;
		}

		return 1;
	}
}
