package com.duobeiyun.repository;

import com.duobeiyun.domain.ArticleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dapeng on 16/8/18.
 */
public interface ArticleInfoRepository extends JpaRepository<ArticleInfo, Long> {

	List<ArticleInfo> findAllByOrderByIdDesc();
}
