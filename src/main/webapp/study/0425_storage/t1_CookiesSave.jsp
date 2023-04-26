<!-- t1_CookiesSave.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String mid = "hkd1234";
	Cookie cookieMid = new Cookie("cMid", mid);
	cookieMid.setMaxAge(60*60*24);		// 쿠키 만료시간 : 단위(초), 1일(60*60*24)
	
	String pwd = "1234";
	Cookie cookiePwd = new Cookie("cPwd", pwd);
	cookiePwd.setMaxAge(60*60*24);
	
	String job = "학생";
	Cookie cookieJob = new Cookie("cJob", job);
	cookieJob.setMaxAge(60*60*24);
	
	// 클라이언트에 쿠키 저장(사용자 컴퓨터에 저장)
	response.addCookie(cookieMid);
	response.addCookie(cookiePwd);
	response.addCookie(cookieJob);
%>
<script>
	alert("쿠키에 저장 완료");
	location.href = "t1_CookiesMain.jsp";
</script>