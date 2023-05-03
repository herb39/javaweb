package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PassOk1Command implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd").toUpperCase();
		int idx = request.getParameter("idx") == null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		System.out.println("= = = = = 원본 자료 = = = = =");
		System.out.println("mid : " + mid);
		System.out.println("pwd : " + pwd);
		System.out.println("idx : " + idx);
		System.out.println();
		
		if (idx == 1) {
			// 숫자 암호화
			// 암호화 키 설정(0x1234ABCD)
			int key = 0x1234ABCD;
			
			int encPwd, decPwd;
			// 암호화 EOR(^)
			encPwd = Integer.parseInt(pwd) ^ key;
			
			System.out.println("= = = 암호화된 비밀번호 = = =");
			System.out.println("encPwd : " + encPwd);
			System.out.println("~~~~~~~~~~DB에 암호화된 비밀번호 저장~~~~~~~~~~");
			System.out.println("암호화된 비밀번호 DB에 저장 -> 인증을 위해 다시 불러와서 디코딩");
			
			decPwd = encPwd ^ key;
			System.out.println("= = = 복호화된 비밀번호 = = =");
			System.out.println("decPwd : " + decPwd);
			System.out.println();
		} else {
			// 혼합 암호화 (영문 소문자 입력 -> 대문자로 변경 후 처리)
			System.out.println("1. 원본 비밀번호 : " + pwd);
			long intPwd;
			String strPwd = "";
			for (int i = 0; i < pwd.length(); i++) {
				intPwd = pwd.charAt(i);
				strPwd += intPwd;
			}
			System.out.println("2. 아스키코드 변환 비밀번호 : " + strPwd);
			
			intPwd = Long.parseLong(strPwd);
			
			long encPwd;
			long key = 0x1234ABCD;
			
			encPwd = intPwd ^ key;
			strPwd = String.valueOf(encPwd);
			System.out.println("3. 인코딩된 비밀번호 DB에 저장시킬 번호 : " + strPwd);
			System.out.println("~ ~ ~ DB 저장 ~ ~ ~");
			
			// DB에 저장된 암호 -> 복호화(디코딩)
			long decPwd;
			intPwd = Long.parseLong(strPwd);
			decPwd = intPwd ^ key;
			System.out.println("4. 복호화(디코딩)된 비밀번호 : " + decPwd);
			
			// 복호화된 비밀번호 : 숫자 -> 문자로 변환 -> 2자리씩 분리
			strPwd = String.valueOf(decPwd);
			
			char ch;
			String result = "";
			for (int i = 0; i < strPwd.length(); i+=2) {
				 ch = (char)Integer.parseInt(strPwd.substring(i, i + 2));
				 result += ch;
			}
			System.out.println("5. 복호화final : " + result);
		}		
	}

}
