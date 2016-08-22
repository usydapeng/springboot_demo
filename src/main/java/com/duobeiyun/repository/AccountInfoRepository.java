package com.duobeiyun.repository;

import com.duobeiyun.domain.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dapeng on 16/8/18.
 */
public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long> {

	AccountInfo findTopByUsername(String username);
}
