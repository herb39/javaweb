package study2.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginOkCommand implements LoginInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");
		
		LoginDAO dao = new LoginDAO();		
		LoginVO vo = dao.getLoginCheck(mid, pwd);
		
						
		if (vo.getName() != null) {		// 회원 인증 성공
			Date today = new Date();
			SimpleDateFormat sdfToday = new SimpleDateFormat("yyyy-MM-dd");
			String strNowDate = sdfToday.format(today);
			
			int todayCount = vo.getTodayCount();
			int point = vo.getPoint();
			String lastDate = vo.getLastDate();
			// 방문 포인트 / 최종 접속일 / 방문 카운트 처리
			// db 최종접속일(10자리)와 시스템날짜(10자리) 비교
			// 같으면 todayCount = vo.getTodayCount()+1 / 다르면 todayCount = 1
			// dao.setPointPlus(mid, todayCount);			

			if (lastDate.substring(0, 10).equals(strNowDate)) {
				todayCount++;
				if (todayCount <= 5) {
					point = vo.getPoint() + 10;
				}
			} else {
				todayCount = 1;
				point = vo.getPoint() + 10;
			}
			
			dao.setPointPlus(mid, point, todayCount);
			
			vo = dao.getLoginCheck(mid, pwd);

			// 1. 자주 사용하는 자료 세션에 저장(아이디, 성명, 닉네임)
			HttpSession session = request.getSession();
			session.setAttribute("sMid", mid);
			session.setAttribute("sName", vo.getName());
			session.setAttribute("sPoint", point);
			session.setAttribute("sTodayCount", vo.getTodayCount());
			session.setAttribute("sLastDate", vo.getLastDate());

			String idSave = request.getParameter("idSave") != null ? "on" : "off";
      System.out.println("idSave : " + idSave);
      Cookie cookieMid = new Cookie("cMid", mid);
      cookieMid.setPath("/");
      if(idSave.equals("on")) {
          cookieMid.setMaxAge(60*60*24*7);
      } else {
          cookieMid.setMaxAge(0);
      }
      response.addCookie(cookieMid);
			
      request.setAttribute("msg", mid + "님 로그인 되었습니다.");
      request.setAttribute("url", request.getContextPath()+"/MemberMain.re");
      
		} else {			// 회원 인증 실패
			request.setAttribute("msg", "로그인 실패");
			request.setAttribute("url", request.getContextPath()+"/Login.re");
			
		}
	}

}
