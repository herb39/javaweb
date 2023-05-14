package study2.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import member.MemberDAO;
import member.MemberVO;

@SuppressWarnings("serial")
@WebServlet("/AjaxTest4")
public class AjaxTest4 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberMidCheck(mid);
				
		HashMap<String, String> map = new HashMap<>();
		map.put("mid", vo.getMid());
		map.put("name", vo.getName());
		map.put("nickName", vo.getNickName());
		map.put("gender", vo.getGender());
		map.put("point", vo.getPoint()+"");
		
//		System.out.println("map : " + map);
		
		// HashMap 형식의 자료 -> 문자열 변환
		// JSON 라이브러리 추가
		// JSON 형식 자료로 변경 / Map 형식의 자료를 저장할 JSON 객체 생성
		JSONObject memberObj = new JSONObject(map);
		System.out.println("memberObj : " + memberObj);
		
		// JSON 객체 -> 문자열 : toJSONString()
		String str = memberObj.toJSONString();
		System.out.println("str : " + str);
		
		// vos 객체 -> JSON 객체
//		JSONArray jsonVOS = new JSONArray();
//		jsonVOS.add(memberObj);
		
		
//		response.getWriter().write(memberObj.toString());
		response.getWriter().write(str);
		
	}
}









