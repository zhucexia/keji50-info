package com.keji50.k5.dao.po;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class InfoCommentPo {

	public InfoCommentPo() {
		
	}
	
	public InfoCommentPo(int authorId, int infoId) {
		this.author = new AccountPo();
		this.author.setId(authorId);
		this.infoId = infoId;
	}
	
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
	private String state = "c";
	
	public String getCreateDate() {
        return createDate == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createDate);
    }

}
