package com.duobeiyun.repository;

import com.duobeiyun.domain.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dapeng on 16/8/18.
 */
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {

	PaymentInfo findTopByOutTradeNo(String outTradeNo);
}
