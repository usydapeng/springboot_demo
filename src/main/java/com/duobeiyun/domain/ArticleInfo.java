package com.duobeiyun.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dapeng on 16/8/18.
 */
@Entity
@Table(name = "article_info")
public class ArticleInfo implements Serializable {

	private static final long serialVersionUID = -8746745175617400935L;

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private String brief;

	private String content;

	private String coverImg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

}
