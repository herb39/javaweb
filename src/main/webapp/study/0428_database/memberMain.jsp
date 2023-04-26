<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/include/memberCheck.jsp" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>memberMain.jsp</title>
	<jsp:include page="/include/bs4.jsp" />
</head>
<body>
	<p><br /></p>
	<div class="container">	
		<h2>회원 전용 페이지</h2>
		<p>현재 ${sMid}(${sName})님 로그인 중입니다.</p>
		<hr />
		<p><img src="${ctp}/images/2.jpg" width="300px"></p>
		<hr />
		<div>
			<a href="${ctp}/database/Logout" class="btn btn-danger">로그아웃</a>
		</div>
	</div>
	<p><br /></p>
</body>
</html>