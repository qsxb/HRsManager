package com.zch.manager.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.zch.manager.bean.Resume;

public class MailUitls {
	/**
	 * 发送邮件的方法
	 * 
	 * @param to
	 *            :收件人
	 * @param code
	 *            :激活码
	 */
	public static void sendMail(String to, int state) {
		/**
		 * 1.获得一个Session对象. 2.创建一个代表邮件的对象Message. 3.发送邮件Transport
		 */
		// 1.获得连接对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 发件方注册的
				return new PasswordAuthentication("service@shop.com", "111");
			}

		});
		// 2.创建邮件对象:
		Message message = new MimeMessage(session);
		// 设置发件人:
		try {
			message.setFrom(new InternetAddress("service@shop.com"));
			// 设置收件人:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 抄送 CC 密送BCC
			// 设置标题
			message.setSubject("来自我的招聘网官方激活邮件");
			// 设置邮件正文:
			message.setContent(
					"<h1>我的招聘网官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://192.168.1.60:8080/HRsManager/userActivation?state="
							+ state + "'>http://192.168.1.60:8080/HRsManager/userActivation?state=" + state
							+ "</a></h3>",
					"text/html;charset=UTF-8");
			// 3.发送邮件:
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	// admint参数表示user是否录用状态
	public static void sendMailAboutFaceTime(String to, String userName, String faceTime, int uid, int rid) {
		/**
		 * 1.获得一个Session对象. 2.创建一个代表邮件的对象Message. 3.发送邮件Transport
		 */
		// 1.获得连接对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 发件方注册的
				return new PasswordAuthentication("service@shop.com", "111");
			}

		});
		// 2.创建邮件对象:
		Message message = new MimeMessage(session);
		// 设置发件人:
		try {
			message.setFrom(new InternetAddress("service@shop.com"));
			// 设置收件人:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 抄送 CC 密送BCC
			// 设置标题
			message.setSubject("海同科技的面试邮件");
			// 设置邮件正文:
			message.setContent("<h1>欢迎你<" + userName + ">请在以下时间前来我们公司面试    .面试时间" + faceTime
					+ "点下面链接模拟完成用户接受面试操作!</h1><h3><a href='http://192.168.1.60:8080/HRsManager/userKnowFaceTime?uid="
					+ uid + "&rid=" + rid + "'>http://192.168.1.60:8080/HRsManager/userKnowFaceTime?uid=" + uid
					+ "&rid=" + rid + "</a></h3>", "text/html;charset=UTF-8");
			// 3.发送邮件:
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void sendMailInfoEmpl(String to, String faceTime, int eid, String ename, Resume resume) {
		/**
		 * 1.获得一个Session对象. 2.创建一个代表邮件的对象Message. 3.发送邮件Transport
		 */
		// 1.获得连接对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 发件方注册的
				return new PasswordAuthentication("service@shop.com", "111");
			}

		});
		// 2.创建邮件对象:
		Message message = new MimeMessage(session);
		// 设置发件人:
		try {
			message.setFrom(new InternetAddress("service@shop.com"));
			// 设置收件人:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 抄送 CC 密送BCC
			// 设置标题
			message.setSubject("通知邮件");
			// 设置邮件正文:
			message.setContent(
					"<h1><" + ename + ">请在以下时间前去面试,有应聘者求职    .时间" + faceTime + "求职者信息" + resume.getRname() + ","
							+ resume.getReducation()
							+ ",点下面链接模拟完成面试操作!</h1><h3><a href='http://192.168.1.60:8080/HRsManager/mockInterview?eid="
							+ eid + "'>http://192.168.1.60:8080/HRsManager/mockInterview?eid=" + eid + "</a></h3>",
					"text/html;charset=UTF-8");
			// 3.发送邮件:
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
