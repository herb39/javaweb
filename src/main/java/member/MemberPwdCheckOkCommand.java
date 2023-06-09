package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conn.SecurityUtil;

public class MemberPwdCheckOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String mid = (String) session.getAttribute("sMid");
		String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberMidCheck(mid);
		
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(vo.getSalt() + pwd);
		
		if (!pwd.equals(vo.getPwd())) {
			request.setAttribute("msg", "비밀번호가 다릅니다.");
			request.setAttribute("url", request.getContextPath() + "/MemberPwdCheckForm.mem");
		} else {
			request.setAttribute("msg", "회원정보 수정 페이지로 이동합니다.");
			request.setAttribute("url", request.getContextPath() + "/MemberUpdate.mem");
		}
	}
}
