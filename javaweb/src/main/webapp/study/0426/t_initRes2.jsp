<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>t_initRes.jsp</title>
	<jsp:include page="/include/bs4.jsp" />
</head>
<body>
	<p><br /></p>
	<div class="container">	
		<h2>init2(Servlet)에서 보낸 값 출력</h2>
		<p>
			부서명 : ${logoName}<br />
			홈페이지 주소 : ${homeAddress}
		</p>
		<p>
			아이디 : ${iMid}<br />
			비밀번호 : ${iPwd}
		</p>
	</div>
	<p><br /></p>
</body>
</html>