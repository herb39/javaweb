package study.t0420;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class susuOk extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] sus = request.getParameterValues("su");
		String strSu = "";
		for(String su : sus) strSu += su + "/";
		strSu = strSu.substring(0, strSu.length()-1);
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/study/0420/susu.jsp");
	  dispatcher.forward(request, response);
	}
}
