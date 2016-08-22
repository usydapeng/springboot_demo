package com.duobeiyun.service;

import com.duobeiyun.domain.ArticleInfo;
import com.duobeiyun.repository.ArticleInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dapeng on 16/8/18.
 */
@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleInfoRepository articleInfoRepository;

	public List<ArticleInfo> getAll() {
		return articleInfoRepository.findAllByOrderByIdDesc();
	}

	public ArticleInfo getById(Long id) {
		return articleInfoRepository.findOne(id);
	}
}
