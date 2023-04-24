package com.yedam.common;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;

public class CoolSMS {
	public static void main(String[] args) {
		String api_key = "NCSK2JUPCEAISRSQ";
		String api_secret="1JDMF1M7C26WJKBYMTR0YWFVPI7YPWFS";
		Scanner sc = new Scanner(System.in);
		Message coolsms = new Message(api_key , api_secret);
		HashMap<String , String> set = new HashMap<>();
		
		String str = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789~!@#$%^&*()_-";
		String code = "";
		Random r = new Random();
		for (int i = 0 ; i < 6 ; i ++) {
			int idx = r.nextInt(str.length());
			code += str.charAt(idx);
		}
		
		set.put("to", "01043885513"); //수신번호
		set.put("from", "01057522287");//발신번호
		set.put("text","회원가입 인증을 위한 코드 입니다, 입력해주세요 \n" + code);//문자내용
		set.put("type", "sms");//문자타입
		
		//json 은 객체로 해쉬맵 처럼 키와 벨류값 의 형태로 생김
		try {
			JSONObject result = coolsms.send(set);
			String resultMsg = result.get("success_count").toString();
			if(resultMsg.equals("1")) {
				System.out.println("인증코드 입력: ");
				String input = sc.next();
				if(input.equals(code)) {
					System.out.println("인증완료!");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}	
