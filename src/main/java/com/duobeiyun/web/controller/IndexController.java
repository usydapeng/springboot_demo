package com.duobeiyun.web.controller;

import com.duobeiyun.config.security.EnhanceSecurityUtils;
import com.duobeiyun.domain.ArticleInfo;
import com.duobeiyun.service.AccountService;
import com.duobeiyun.service.ArticleService;
import com.oldpeng.core.alipay.AlipayPaymentBean;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by dapeng on 16/8/18.
 */
@Controller
public class IndexController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private AccountService accountService;

	@RequestMapping({"/shop", ""})
	public String index(HttpServletRequest request, Model model){
		String outTradeNo = request.getParameter("out_trade_no");
		String tradeStatus = request.getParameter("trade_status");
		String totalFee = request.getParameter("total_fee");
		if(StringUtils.isNotBlank(outTradeNo) && StringUtils.isNotBlank(tradeStatus) && StringUtils.isNotBlank(totalFee)){
			accountService.updatePayment(outTradeNo, tradeStatus, totalFee);
			return "redirect:/shop";
		}

		List<ArticleInfo> articleInfoList = articleService.getAll();
		model.addAttribute("articleList", articleInfoList);
		return "index";
	}

	@RequestMapping({"/article/{id}", "/shop/article/{id}"})
	public String detail(@PathVariable Long id, Model model){
		ArticleInfo articleInfo = articleService.getById(id);
		model.addAttribute("article", articleInfo);
		return "detail";
	}

	@RequestMapping({"/purchase", "/shop/purchase"})
	@RequiresUser
	public String purchase(@RequestHeader("Host") String host, Model model){
		if(accountService.getById(EnhanceSecurityUtils.retrieveEnhanceUser().getAccountId()).getRole() == 1){
			return "redirect:/shop/";
		}

		AlipayPaymentBean alipayPaymentBean = accountService.getPaymentBean(EnhanceSecurityUtils.retrieveEnhanceUser().getAccountId(), host);
		model.addAttribute("paymentBean", alipayPaymentBean);

		return "zhifubao";
	}

	@RequestMapping({"/qidai", "/shop/qidai"})
	public String qidai(){

		return "qidai";
	}
}
