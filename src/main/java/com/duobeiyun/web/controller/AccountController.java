package com.duobeiyun.web.controller;

import com.duobeiyun.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by dapeng on 16/8/18.
 */
@Controller
public class AccountController {

	private static Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@RequestMapping({"/login", "/shop/login"})
	public String index(@RequestParam(value = "hello", required = false, defaultValue = "0") int type, Model model){
		model.addAttribute("type", type);
		return "login";
	}

	@RequestMapping(value = {"/login", "/shop/login"}, method = RequestMethod.POST)
	public String login(@RequestParam(value = "username", required = false) String username,
	                    @RequestParam(value = "passwd", required = false) String passwd){

		if(StringUtils.isBlank(username) || StringUtils.isBlank(passwd)){
			return "login";
		}

		try {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, passwd);
			SecurityUtils.getSubject().login(usernamePasswordToken);
		} catch(Throwable t){
			logger.info(t.getMessage(), t);
			return "login";
		}

		return "redirect:/shop";
	}

	@RequestMapping({"/register", "/shop/register"})
	public String registerNew(){

		return "register";
	}

	@RequestMapping(value = {"/register", "/shop/register"}, method = RequestMethod.POST)
	public String register(@RequestParam(value = "username", required = false) String username,
	                       @RequestParam(value = "passwd", required = false) String passwd,
	                       RedirectAttributes redirectAttributes){
		if(StringUtils.isBlank(username) || StringUtils.isBlank(passwd)){
			return "register";
		}

		try {
			accountService.createAccount(username, passwd);
		} catch(Throwable t){
			logger.info(t.getMessage(), t);
			return "register";
		}

		redirectAttributes.addAttribute("hello", 1);

		return "redirect:/shop/login";
	}
}
