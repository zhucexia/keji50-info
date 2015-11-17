package com.keji50.k5.service.out.sms;

import lombok.Getter;

public enum SmsTemplate {

	VALIDATION_TEMPLATE("0", "您的验证码是：%s。请不要把验证码泄露给其他人。");

	@Getter
	private String type;

	@Getter
	private String template;

	private SmsTemplate(String type, String template) {
		this.type = type;
		this.template = template;
	}

	public static String getTemplate(String type) {
		for (SmsTemplate smsTemplate : values()) {
			if (smsTemplate.getType().equals(type)) {
				return smsTemplate.getTemplate();
			}
		}

		return "";
	}
}
