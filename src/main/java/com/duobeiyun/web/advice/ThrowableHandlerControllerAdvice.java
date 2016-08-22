package com.duobeiyun.web.advice;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ThrowableHandlerControllerAdvice {

	private static Logger logger = LoggerFactory.getLogger(ThrowableHandlerControllerAdvice.class);

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNoSuchElementException(Throwable e) {
		logger.info(e.getMessage(), e);
		return "redirect:/shop/error";
	}

	@ExceptionHandler(AuthorizationException.class)
	public String handleAccessDeniedException(AuthorizationException e, HttpServletRequest request){
		logger.info(e.getMessage(), e);
		return "redirect:/shop/login";
	}
}
