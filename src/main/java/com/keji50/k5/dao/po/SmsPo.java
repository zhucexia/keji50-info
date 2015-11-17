package com.keji50.k5.dao.po;

import com.keji50.k5.service.out.sms.SmsTemplate;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SmsPo extends BasePo {

	public SmsPo() {

	}

	public SmsPo(String mobile, String type) {
		this.mobile = mobile;
		this.type = type;
	}

	private int id;

	// 短信类型, 0: 手机绑定短信
	private String type;

	// 手机号
	private String mobile;

	// 验证码
	private String validationCode;

	// 有效时间
	private Date validationExpire;

	// 客户IP地址
	private String ip;

	private String smsId;

	public String getSmsContent() {
		return String.format(SmsTemplate.getTemplate(type), validationCode);
	}

}
