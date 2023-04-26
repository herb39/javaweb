<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>title</title>
	<jsp:include page="/include/bs4.jsp" />
</head>
<body>
	<p><br /></p>
	<div class="container">
		<form name="myform" method="post" action="${ctp}/database/LoginOk">
			<table class="table table-bordered text-center">
				<tr>
					<td colspan="2"><font size="5">로 그 인</font></td>
				</tr>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="mid" autofocus required class="form-control"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd" required class="form-control"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="로그인" class="btn btn-success m-2">
						<input type="reset" value="다시입력" class="btn btn-warning m-2">
						<input type="button" value="회원가입" onclick="location.href='join.jsp';" class="btn btn-primary m-2">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<p><br /></p>
</body>
</html>