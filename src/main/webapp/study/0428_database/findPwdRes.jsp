<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>findPwdRes.jsp</title>
	<jsp:include page="/include/bs4.jsp" />
</head>
<body>
<p><br /></p>
<div class='container text-center'>
	<table class="table table-bordered">
		<tr>
			<th>비밀번호 찾기 결과</th>
		</tr>
		<tr>
			<th>아이디</th>
			<td>${mid}</td>
		</tr>
		<tr>
			<th>성명</th>
			<td>${name}</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<%-- 
				<c:set var="content"
				<c:forEach var="i" begin="0" end="${pwd.length()-1}" />
					<c:if test="${i % 2 == 0}">${fn:substring()}</c:if>
				 --%>
			</td>
		</tr>
		<tr>
			<td>
				<input type="button" value="돌아가기" onclick="location.href='findPwd.jsp';" class="btn btn-danger" />
			</td>	
		</tr>
	</table>
</div>
<p><br /></p>
</body>
</html>