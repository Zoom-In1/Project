package com.example.CoffeeShop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {
	
	// JavaMailSender를 사용해 이메일 보냄
	private final JavaMailSender javaMailSender;
	// 발송자 이메일 주소 (application.properties에서 읽어오는 발송자 이메일 주소)
	@Value("${spring.mail.username}")
	private String senderEmail;
	// 생성된 인증 번호를 저장하는 변수
	private static int number;
	
	// 랜덤 숫자 생성
	public static void CreateNumber() {
		
		number = (int)(Math.random() * (900000)) + 100000; // 6자리 숫자 생성
	}
	
	public MimeMessage CreateMail(String mail) { // 매일 생성
		// 랜덤 인증 번호 생성
		CreateNumber();
		// MimeMessage 객체 생성 (이메일 내용 작성)
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			// HTML 본문 작성
            StringBuilder body = new StringBuilder();
            body.append("<hr style='background:#007bff; height:5px; border:0;'>");
            body.append("<h3>요청하신 이메일 인증 번호를 안내 드립니다.</h3>");
            body.append("<h3>아래 번호를 입력하여 CoffeeShop 인증 절차를 완료해 주세요.</h3>");
            body.append("<h1>").append(number).append("</h1>"); // 생성된 인증 번호 표시
            body.append("<h3>감사합니다.</h3>");
            // 메시지 설정
            message.setFrom(senderEmail); // 발송자 이메일 설정
            message.setRecipients(MimeMessage.RecipientType.TO, mail); // 수신자 이메일 설정
            message.setSubject("CoffeeShop 회원가입 인증번호 안내"); // 이메일 제목 설정
            message.setText(body.toString(), "UTF-8", "html"); // 본문 내용을 HTML 형식으로 설정
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message; // 완성된 MimeMessage 객체 반환
    }

    public int sendMail(String mail) {
    	
        MimeMessage message = CreateMail(mail); // 이메일 메시지 생성
        javaMailSender.send(message); // 이메일 전송

        return number;  // 생성된 인증 번호 반환
    }
}
