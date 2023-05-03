<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, inital-scale=1">
	<title>password.jsp</title>
	<jsp:include page="/include/bs4.jsp" />
	<script>
		'use strict';
		
		function pwdCheck(idx) {
			let pwd = myform.pwd.value.trim();
			myform.idx.value = idx;
			
			if (pwd == "") {
				alert("비밀번호를 입력하세요");
				myform.pwd.focus();
			} else {
				if (idx == 1) {
					myform.action = "${ctp}/PassOk1.st";					
				} else if (idx == 2) {
					myform.action = "${ctp}/PassOk1.st";
				} else {
					myform.action = "${ctp}/PassOk2.st";					
				}
				myform.submit();
			}
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
	<p><br /></p>
	<div class="container">	
		<h2>비밀번호 암호화 연습</h2>
		<p>(비밀번호를 10자 이내로 입력하세요.)</p>
		<hr />
		<form name="myform" method="post">
			<table class="table table-bordered">
				<tr>
					<td>
						<p>아이디 : <input type="text" name="mid" value="hkd1234" class="form-control" /></p>
						<p>비밀번호 : <input type="password" name="pwd" maxlength="9" autofocus class="form-control" /></p>
						<p>
							<input type="button" value="확인(숫자비밀번호)" onclick="pwdCheck(1)" class="btn btn-success" />
							<input type="button" value="확인(혼합비밀번호)" onclick="pwdCheck(2)" class="btn btn-primary" />
							<input type="button" value="확인(SHA-256)" onclick="pwdCheck(3)" class="btn btn-secondary" />
						</p>
					</td>
				</tr>
			</table>
			<input type="hidden" name="idx" />
		</form>
		<hr/>
		<h3>SHA(Secure Hash Algorithm)</h3>
		<pre>
		: SHA 단방향식 암호화 기법 / 암호학적 해시함수들의 모임
		SHA-2 : 복호화 불가
		해시함수 출력문장 : 다이제스트 Digest
		SHA-2가 생성하는 다이제스트의 출력길이 : 256, 512Bit
		SHA-2 + 256Bit = SHA-256 암호화 방식
		</pre>
	</div>
	<p><br /></p>
	<jsp:include page="/include/footer.jsp" />
</body>
</html>