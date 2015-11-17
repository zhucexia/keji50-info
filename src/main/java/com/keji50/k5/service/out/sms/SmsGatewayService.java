package com.keji50.k5.service.out.sms;

import com.keji50.k5.dao.po.SmsPo;
import com.keji50.k5.service.out.http.HttpClientService;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

/**
 * 短信网关服务，  异步发送短信验证码
 * @author chao.li
 *
 */
public class SmsGatewayService implements DisposableBean {

	private static final Logger log = LoggerFactory.getLogger(SmsGatewayService.class);

	@Setter
	private String smsUrl;

	@Setter
	private String account;

	@Setter
	private String password;

	@Setter
	private HttpClientService httpClientService;

	// 处理线程池， 短信网关异步发送短信
	private ExecutorService pool = Executors.newFixedThreadPool(10);

	/**
	 * 异步发送短信验证码
	 * @param sms
	 */
	public void sendSms(final SmsPo sms) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("account", account);
				params.put("password", password);
				params.put("mobile", sms.getMobile());
				params.put("content", sms.getSmsContent());

				try {
					httpClientService.post(smsUrl, params);
				} catch (Exception e) {
					log.error("fail to send sms", e);
				}
			}
		});
		
	}

//	private SmsGatewayPo toBean(String xml) throws DocumentException {
//		Document doc = DocumentHelper.parseText(xml);
//		Element root = doc.getRootElement();
//
//		SmsGatewayPo po = new SmsGatewayPo();
//		if (root.element("code") != null) {
//			po.setCode(root.elementText("code"));
//		}
//		if (root.element("msg") != null) {
//			po.setMsg(root.elementText("msg"));
//		}
//		if (root.element("smsid") != null) {
//			po.setSmsid(root.elementText("smsid"));
//		}
//		return po;
//	}

	@Override
	public void destroy() throws Exception {
		if (pool != null) {
			pool.shutdown();
		}
	}
}
