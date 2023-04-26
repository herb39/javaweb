package study.t0424;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/t0424/T6_LoginOk")
public class T6_LoginOk extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");
		String idSave = request.getParameter("idSave") == null ? "off" : "on";
//		String viewPage = "";
		
		HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
		
		if ((mid.equals("admin") && pwd.equals("1234")) || (mid.equals("hkd1234") && pwd.equals("1234"))) {
//			request.setAttribute("mid", mid);
			session.setAttribute("sMid", mid);
			
			Cookie cookidMid = new Cookie("cMid", mid);
			cookidMid.setPath("/"); 		// 웹 어플리케이션의 전체경로에서 사용하고자 할 때 "/" 지정
			if(idSave.equals("on")) {
				cookidMid.setMaxAge(60*60*24*7); 		// 쿠키 만료 시간 일주일
			} else {
				cookidMid.setMaxAge(0);
			}
			response.addCookie(cookidMid);
			
			out.print("<script>");
			out.print("alert('"+mid+"님 로그인 되었습니다.');");
			out.print("location.href='"+request.getContextPath()+"/study/0425_storage/t6_member.jsp';");
			out.print("</script>");
//			viewPage = "/study/0425_storage/t5_member.jsp";
//			request.getRequestDispatcher(viewPage).forward(request, response);
		} else {
			out.print("<script>");
			out.print("alert('로그인에 실패했습니다.');");
			out.print("location.href='"+request.getContextPath()+"/study/0425_storage/t6_Login.jsp';");
			out.print("</script>");
//			viewPage = "/study/0425_storage/t5_Login.jsp";
//			request.getRequestDispatcher(viewPage).forward(request, response);
		}
	}
}