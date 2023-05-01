package study2.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.database.LoginDAO;
import study.database.LoginVO;

public class LoginJoinOkCommand implements LoginInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String msg = "", url = "";
		if (vo2.getMid() != null) {
			// 아이디 중복
			msg = "이미 사용중인 아이디입니다.";
			url = "/Join.re";
		} else {
			// 아이디 DB에 저장(중복x)
			dao.setJoinOk(vo);
			msg = "회원가입 완료";
			url = "/Login.re";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", request.getContextPath()+url);
	}
}
