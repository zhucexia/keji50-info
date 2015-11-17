package com.keji50.k5.dao.po;

import java.util.Date;

import lombok.Data;

@Data
public class BasePo {
	
	private String createBy;
	
	private Date createTime;
	
	private String updateBy;
	
	private Date updateTime;
	
}
