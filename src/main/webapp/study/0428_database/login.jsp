<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
   Cookie[] cookies = request.getCookies();  // 저장된 쿠키를 배열로 받아온다!
   
   if(cookies != null) {  // NullPointerException 오류가 발생할 수 있기 때문에 != 조건을 준다.
      for(int i=0; i<cookies.length; i++) {
         if(cookies[i].getName().equals("cMid")){  // cookie 배열에 있는 값이 저장된 cMid와 같다면
            pageContext.setAttribute("mid", cookies[i].getValue()); // pageContext에 그 값을 저장하고 mid에 value로 찍어주면 아이디 저장완료! 
            break;
         }
      }
   }
%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>login.jsp</title>
	<jsp:include page="/include/bs4.jsp" />
</head>
<body>
	<jsp:include page="/include/header.jsp" />
	<p><br /></p>
	<div class="container">
		<form name="myform" method="post" action="${ctp}/database/LoginOk">
			<table class="table table-bordered text-center">
				<tr>
					<td colspan="2"><font size="5">로 그 인</font></td>
				</tr>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="mid" size="20" autofocus required class="form-control"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd" required class="form-control"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="로그인" class="btn btn-success mr-3">
						<input type="checkbox" value="idSave"> 아이디 저장<br />
						<input type="button" value="회원가입" onclick="location.href='join.jsp';" class="btn btn-primary mt-2 mr-3">
						<a href="findPwd.jsp" id="findPwd"><span class="findPwd">비밀번호를 잊으셨나요?</span></a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<p><br /></p>
	<jsp:include page="/include/footer.jsp" />
</body>
</html>