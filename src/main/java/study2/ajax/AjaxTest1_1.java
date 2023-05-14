package study2.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

@SuppressWarnings("serial")
@WebServlet("/AjaxTest1_1")
public class AjaxTest1_1 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberMidCheck(mid);
				
		
		PrintWriter out = response.getWriter();
		if (vo.getName() == null) {
			out.print("<script>");
			out.print("alert('찾고자 하는 회원정보가 없습니다.');");
		} else {
			out.print("<script>");
			out.print("alert('검색된 회원은 "+vo.getName()+" 입니다.');");
		}
		out.print("location.href='"+request.getContextPath()+"/AjaxTest1.st';");
		out.print("</script>");
	}
}
