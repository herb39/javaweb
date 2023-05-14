package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/database/JoinOk")
public class JoinOk extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");
		String name = request.getParameter("name") == null ? "" : request.getParameter("name");
		
		LoginVO vo = new LoginVO();
		
		vo.setMid(mid);
		vo.setPwd(pwd);
		vo.setName(name);
		
		LoginDAO dao = new LoginDAO();
		
		// 중복 아이디 체크
		LoginVO vo2 = dao.getMidCheck(mid);
		
		PrintWriter out = response.getWriter();
		
		if (vo2.getMid() != null) {
			// 아이디 중복
			out.print("<script>");
			out.print("alert('이미 사용중인 아이디입니다.');");
			out.print("location.href='"+request.getContextPath()+"/study/0428_database/join.jsp'");
			out.print("</script>");
		} else {
			// 아이디 DB에 저장
			dao.setJoinOk(vo);
			out.print("<script>");
			out.print("alert('회원가입 완료!!');");
			out.print("location.href='"+request.getContextPath()+"/study/0428_database/join.jsp'");
			out.print("</script>");
		}
		
	}
}
