package com.duobeiyun.config.security.shiro;

import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dapeng on 16/8/10.
 */
public class ShopShiroFilter extends AbstractShiroFilter {

	private static Logger logger = LoggerFactory.getLogger(ShopShiroFilter.class);

	public ShopShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver){
		super();
		if (webSecurityManager == null) {
			throw new IllegalArgumentException("Zeus WebSecurityManager property cannot be null.");
		}
		setSecurityManager(webSecurityManager);
		if (resolver != null) {
			setFilterChainResolver(resolver);
		}
	}

	@Override
	protected ServletResponse wrapServletResponse(HttpServletResponse response, ShiroHttpServletRequest request) {
//		logger.info("------------------------ zeus shiro filter");
		return new ShopShiroHttpServletResponse(response, getServletContext(), request);
	}

}
