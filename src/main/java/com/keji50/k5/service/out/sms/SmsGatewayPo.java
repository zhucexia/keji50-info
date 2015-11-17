package com.keji50.k5.service.out.sms;

import lombok.Data;

@Data
public class SmsGatewayPo {

	// 短信网关返回码
	private String code;

	// 短信网关返回内容
	private String msg;

	// 短信网关返回消息ID
	private String smsid;

	/**
	 * 消息是否发送成功
	 */
	public boolean isSuccess() {
		return "2".equals(code);
	}
}
