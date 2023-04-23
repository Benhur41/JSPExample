package com.yedam.common;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SMTP {
	static Properties prop;
	static Session session;
	static MimeMessage message;
	
	public static void main(String[] args) throws Exception {
		generateAndSendEmail("안녕하세요","김태연입니다");
		System.out.println("\n\n ====> Your Java Program just sent an Email successfully . Check your email..");
	}
	
//1   프로퍼티 생성
	public static void generateAndSendEmail(String title , String content) throws Exception{
	final String user = "hbj04003@gmail.com";
	final String password = "ralsmhexrxqbrimj";
	
	prop = new Properties();
	prop.put("mail.smtp.host", "smtp.gmail.com");// 네이버를 smtp 서버로 사용할 경우 "smtp.naver.com"
	prop.put("mail.smtp.port", 465);//네이버 일시 포트번호 587
	prop.put("mail.smtp.auth", "true");
	prop.put("mail.smtp.ssl.enable", "true");
	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//2   세션 설정
	Session session = Session.getDefaultInstance(prop,new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user,password);
		}
	});
//3   메세지작성
	try {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		//수신자 메일 주소
		//다중메일 설정
		//InternetAddress[] 배열을 만들어서 2번째 매개변수에 배열을 넣어준다
		//ex) InternetAddress[] emailArray = new InternetAddress[5];
		//array[0]="hsd@sdsd"
		//array[1]=sdsd@sdsd"
		//array[2]="wwww@ssss"
		//array[3]=sddd@ssd"
		//array[4]=fff@sds"
		//message.addRecipient(Message.RecipientType.TO, emailArray);
		message.addRecipient(Message.RecipientType.TO, new InternetAddress("hbj04003@naver.com"));
		
		
		//subject
		message.setSubject(title);
		//text
		message.setText(content);
		
		
//4   이메일보내기
		Transport.send(message);
		System.out.println("message sent successfully!!");
	}catch(AddressException e) {
		e.printStackTrace();
	}catch(MessagingException e) {
		e.printStackTrace();
	}
	
	
	}
	
	
}
