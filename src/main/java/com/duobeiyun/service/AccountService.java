package com.duobeiyun.service;

import com.alibaba.fastjson.JSONObject;
import com.duobeiyun.domain.AccountInfo;
import com.duobeiyun.domain.PaymentInfo;
import com.duobeiyun.repository.AccountInfoRepository;
import com.duobeiyun.repository.PaymentInfoRepository;
import com.oldpeng.core.alipay.AlipayPaymentBean;
import com.oldpeng.core.utils.ApiUtils;
import com.oldpeng.core.utils.UuidUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by dapeng on 16/8/18.
 */
@Service
@Transactional
public class AccountService {

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountInfoRepository accountInfoRepository;

	@Autowired
	private PaymentInfoRepository paymentInfoRepository;

	@Value("${alipay.partner}")
	private String partner;

	@Value("${alipay.key}")
	private String key;

	@Value("${alipay.seller.email}")
	private String sellerMail;

	@Value("${alipay.notify_url}")
	private String notifyUrl;

	@Value("${alipay.return_url}")
	private String returnUrl;

	public AccountInfo getById(Long accountId) {
		return accountInfoRepository.findOne(accountId);
	}

	public void createAccount(String username, String passwd) {
		if(StringUtils.isBlank(username) || StringUtils.isBlank(passwd)){
			throw new RuntimeException("username or password is not blank");
		}

		AccountInfo accountInfo = accountInfoRepository.findTopByUsername(username);
		if(accountInfo != null){
			throw new RuntimeException("user has been exist");
		}

		accountInfo = new AccountInfo();
		accountInfo.setUsername(username);
		accountInfo.setPasswd(passwd);
		accountInfo.setCreateTime(new Date());
		accountInfoRepository.save(accountInfo);
	}

	public AlipayPaymentBean getPaymentBean(Long accountId, String host) {
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setOutTradeNo(UuidUtils.generate());
		paymentInfo.setAccountId(accountId);
		paymentInfoRepository.save(paymentInfo);

		AlipayPaymentBean alipayPaymentBean = new AlipayPaymentBean();

		alipayPaymentBean.setPartner(partner);
		alipayPaymentBean.setSellerEmail(sellerMail);
		alipayPaymentBean.setOutTradeNo(paymentInfo.getOutTradeNo());
		alipayPaymentBean.setTotalFee(599.0);
//		alipayPaymentBean.setTotalFee(0.01);
		alipayPaymentBean.setSubject("订阅期刊");
		alipayPaymentBean.setBody("订阅期刊");
		alipayPaymentBean.setNotifyUrl(notifyUrl);
		alipayPaymentBean.setReturnUrl("http://" + host + "/shop");
		alipayPaymentBean.setItBPay("30m");
		alipayPaymentBean.setPaymentType(1);


		logger.info("payment bean: " + JSONObject.toJSONString(alipayPaymentBean));

		try {
			String preSign = ApiUtils.buildParamStr(alipayPaymentBean.retrieveStringProp(), false, true) + key;
			logger.info("pre sign: " + preSign);
			alipayPaymentBean.setSign(DigestUtils.md5Hex(preSign.getBytes("UTF-8")));
		} catch(Throwable t){
			logger.info(t.getMessage(), t);
		}

		return alipayPaymentBean;
	}

	public void updatePayment(String outTradeNo, String tradeStatus, String totalFee) {
		if("TRADE_SUCCESS".equals(tradeStatus)
				|| "TRADE_PENDING".equals(tradeStatus)
				|| "TRADE_FINISHED".equals(tradeStatus)){
			PaymentInfo paymentInfo = paymentInfoRepository.findTopByOutTradeNo(outTradeNo);
			paymentInfo.setStatus(1);
			paymentInfo.setTotalFee(Double.valueOf(totalFee));

			AccountInfo accountInfo = accountInfoRepository.findOne(paymentInfo.getAccountId());
			accountInfo.setRole(1);
		}

	}
}
