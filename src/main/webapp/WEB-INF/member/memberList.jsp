<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>memberList.jsp</title>
	<jsp:include page="/include/bs4.jsp" />
</head>
<body>
<jsp:include page="/include/header.jsp" />
	<p><br /></p>
	<div class="container">
		<h2>전체 회원 리스트</h2>
		<br />
		<table class="table table-borderless m-0 p-0">
			<tr>
				<td class="text-right">
        			<!-- 한페이지 분량처리 -->
			        <select name="pageSize" id="pageSize" onchange="pageCheck()">
			          <option <c:if test="${pageSize == 3}">selected</c:if>>3</option>
			          <option <c:if test="${pageSize == 5}">selected</c:if>>5</option>
			          <option <c:if test="${pageSize == 10}">selected</c:if>>10</option>
			          <option <c:if test="${pageSize == 15}">selected</c:if>>15</option>
			          <option <c:if test="${pageSize == 20}">selected</c:if>>20</option>
			        </select> 건
			      </td>
			</tr>
		
		</table>
		<table class="table table-hover text-center">
			<tr class="table-dark text-dark">
				<th>번호</th>
				<th>아이디</th>
				<th>별명</th>
				<th>성명</th>
				<th>성별</th>
			</tr>
			<c:forEach var="vo" items="${vos}" varStatus="st">
				<tr>
					<td>${vo.idx}</td>
					<td>${vo.mid}</td>
					<td>${vo.nickName}</td>
					<td>
						<c:if test="${vo.userInfor == '공개'}">${vo.name}</c:if>
						<c:if test="${vo.userInfor != '비공개'}"><font color="gray">비공개</font></c:if>
					</td>
					<td>${vo.gender}</td>
				</tr>
			</c:forEach>
			<tr><td colspan="5" class="m-0 p-0"> </td></tr>
		</table>
	</div>
	<p><br /></p>
	<jsp:include page="/include/footer.jsp" />
</body>
</html>