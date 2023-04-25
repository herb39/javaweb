package study.t0424;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/t0424/Test1Ok")
public class Test1Ok extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		필터에서 한글처리 -> 아래 2줄 생략가능
		//		request.setCharacterEncoding("utf-8");
		//		response.setContentType("text/html; charset=utf-8");
		
		// Null 체크
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		int age = request.getParameter("age")==null ? 0 : Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender")==null ? "" : request.getParameter("gender");
		String job = request.getParameter("job")==null ? "" : request.getParameter("job");
		String address = request.getParameter("address")==null ? "" : request.getParameter("address");
		
		// DB에 삽입 : 입력받은 변수의 값을 vo에 담아서 처리
		Test1VO vo = new Test1VO();
		
		vo.setName(name);
		vo.setAge(age);
		vo.setGender(gender);
		vo.setJob(job);
		vo.setAddress(address);
		
		// vo에 담긴 자료 -> DB에 저장
		
		// 처리된 자료들을 view에 보여주기 위해서는 저장소(request)에 담아서 넘겨준다.
		request.setAttribute("vo", vo);
		
		String viewPage = "/study/0424/test1Res.jsp";
//		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
//		dispatcher.forward(request, response);
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
