<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String mid = session.getAttribute("sMid") == null ? "" :(String)session.getAttribute("sMid");
	if(mid.equals("")) {
		out.print("<script>");
		out.print("alert('로그인 후 사용하세요.');");
		out.print("location.href='"+request.getContextPath()+"/study/0425_storage/t6_Login.jsp';");		
		out.print("</script>");
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>t6_member.jsp</title>
	<jsp:include page="/include/bs4.jsp" />
</head>
<body>
	<p><br /></p>
	<div class="container">	
		<h2>이곳은 회원전용 페이지입니다.</h2>
		<hr />
		로그인 중 : ${sMid}
		<hr />
		<p><a href="<%=request.getContextPath() %>/t0424/T6_LoginOut" class="btn btn-danger">로그아웃</a></p>
	</div>
	<p><br /></p>
</body>
</html>