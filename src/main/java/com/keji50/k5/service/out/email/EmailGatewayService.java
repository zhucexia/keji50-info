package com.keji50.k5.service.out.email;

import com.keji50.k5.dao.po.EmailPo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailGatewayService implements DisposableBean {

	private static final Logger log = LoggerFactory.getLogger(EmailGatewayService.class);

	@Setter
	private String mailFrom;

	@Setter
	private String baseValidationUrl;

	@Setter
	private JavaMailSenderImpl mailSender;
	
	// 处理线程池， 邮件网关异步发�?验证邮件
	private ExecutorService pool = Executors.newFixedThreadPool(10);

	public void sendEmail(final EmailPo email) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), false, "GB2312");
					mimeMessageHelper.setFrom(new InternetAddress(MimeUtility.encodeText("keji50") + " <" + mailFrom + ">"));
					mimeMessageHelper.setTo(email.getEmail());
					mimeMessageHelper.setSubject("科技50验证邮件");
					mimeMessageHelper.setText(email.getEmailContent(baseValidationUrl), true);
					
					mailSender.send(mimeMessageHelper.getMimeMessage());
				} catch (Exception e) {
					log.error("failed to send email", e);
				}
			}
		});
	}

	@Override
	public void destroy() throws Exception {
		if (pool != null) {
			pool.shutdown();
		}
	}
}
