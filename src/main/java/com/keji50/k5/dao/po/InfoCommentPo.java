package com.keji50.k5.dao.po;

import java.util.Date;
import lombok.Data;

@Data
public class InfoCommentPo {

	/**
	 * 主键ID
	 */
	private int id;

	/**
	 * 资讯ID
	 */
	private int infoId;

	/**
	 * 评论人
	 */
	private AccountPo author;

	/**
	 * 被评论人
	 */
	private String toAuthor;

	/**
	 * 评论内容
	 */
	private String content;

	/**
	 * 评论时间
	 */
	private Date createDate;

	/**
	 * 评论状态
	 */
	private String state;

}
