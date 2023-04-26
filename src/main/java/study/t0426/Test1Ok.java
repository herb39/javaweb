package study.t0426;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/t0426/Test1Ok")
public class Test1Ok extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 doGet()");

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println("title(doGet) : " + title);
		System.out.println("content(doGet) : " + content);
		
		String viewPage = "/study/0426/test1Res.jsp";
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 doPost()");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println("title(doPost) : " + title);
		System.out.println("content(doPost) : " + content);
		
		doGet(request, response);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 service()");	
		
		doPost(request, response);
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("이곳은 init()");	
	}
	
	@Override
	public void destroy() {
		System.out.println("이곳은 destroy()");	
	}
}
