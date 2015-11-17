package com.keji50.k5.service.out.email;

import lombok.Getter;

public enum EmailTemplate {
	
	VALIDATION_TEMPLATE("0", "点击下面链接�?��邮箱�?8小时有效，链接只能使用一次，请尽快激活！<a href='%s' target='_blank'>点击�?��</a>"),
	RESET_PASSWORD_TEMPLATE("1", "点击下面链接重置密码�?8小时有效，链接只能使用一次，请尽快使用！<a href='%s' target='_blank'>点击重置</a>");

	@Getter
	private String type;

	@Getter
	private String template;
	
	private EmailTemplate(String type, String template) {
		this.type = type;
		this.template = template;
	}
	
	public static String getTemplate(String type) {
		for (EmailTemplate emailTemplate : values()) {
			if (emailTemplate.getType().equals(type)) {
				return emailTemplate.getTemplate();
			}
		}

		return "";
	}
}
