package com.duobeiyun.config.security;

import com.duobeiyun.utils.BeanCopyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class EnhanceSecurityUtils extends SecurityUtils {

	public static EnhanceUser retrieveEnhanceUser() {
		Subject subject = getSubject();
		if (subject == null) {
			return new NullEnhanceUser();
		}
		Object principal = subject.getPrincipal();
		if (principal != null && principal.getClass().toString().equals(EnhanceUser.class.toString())) {
			EnhanceUser enhanceUser = BeanCopyUtils.copy(principal, EnhanceUser.class);
			return enhanceUser;
		}
		return new NullEnhanceUser();
	}

}
