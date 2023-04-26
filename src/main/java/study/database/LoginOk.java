package study.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/database/LoginOk")
public class LoginOk extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");
		
		LoginDAO dao = new LoginDAO();
		
		LoginVO vo = dao.getLoginCheck(mid, pwd);
		
		PrintWriter out = response.getWriter();
		
		Date today = new Date();
		SimpleDateFormat sdfToday = new SimpleDateFormat("yyyy-MM-dd");
		String strNowDate = sdfToday.format(today);
		
		int todayCount = vo.getTodayCount();
		int point = vo.getPoint();
		String lastDate = vo.getLastDate();
		
		
		if (vo.getName() != null) {
			// 회원 인증 성공
			// 방문 포인트 / 최종 접속일 / 방문 카운트 처리
			// db 최종접속일(10자리)와 시스템날짜(10자리) 비교
			// 같으면 todayCount = vo.getTodayCount()+1 / 다르면 todayCount = 0
			// dao.setPointPlus(mid, todayCount);
			
			// 1. 자주 사용하는 자료 세션에 저장(아이디, 성명, 닉네임)
			HttpSession session = request.getSession();
			session.setAttribute("sMid", mid);
			session.setAttribute("sName", vo.getName());
			session.setAttribute("sPoint", vo.getPoint());
			session.setAttribute("sTodayCount", vo.getTodayCount());
			session.setAttribute("sLastDate", vo.getLastDate());
			
			
			if (vo.getLastDate().substring(0, 10).equals(strNowDate)) {
				todayCount++;
				if (todayCount <= 5) {
					point = point + 10;					
				}
			} else {
				todayCount = 1;
				point = point + 10;
			}
			
			dao.setPointPlus(mid, point, todayCount);
			
			vo = dao.getLoginCheck(mid, pwd);
			
			out.print("<script>");
			out.print("alert('"+mid+"님 로그인 되었습니다.');");
			out.print("location.href='"+request.getContextPath()+"/study/0428_database/memberMain.jsp';");
			out.print("</script>");
		} else {
			// 회원 인증 실패
			out.print("<script>");
			out.print("alert('로그인 실패');");
			out.print("location.href='"+request.getContextPath()+"/study/0428_database/login.jsp';");
			out.print("</script>");
		}
	}
}
