package com.duobeiyun.web.controller;

import com.duobeiyun.service.AccountService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dapeng on 16/8/18.
 */
@Controller
public class PaymentController {

	@Autowired
	private AccountService accountService;

	@RequestMapping({"/shop/alipay/payment/callback", "/alipay/payment/callback"})
	@ResponseBody
	public String registerNew(HttpServletRequest request){
		String outTradeNo = request.getParameter("out_trade_no");
		String tradeStatus = request.getParameter("trade_status");
		String totalFee = request.getParameter("total_fee");
		accountService.updatePayment(outTradeNo, tradeStatus, totalFee);
		return "success";
	}
}
