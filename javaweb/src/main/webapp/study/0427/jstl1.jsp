<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>jstl1.jsp</title>
	<jsp:include page="/include/bs4.jsp" />
</head>
<body>
	<p><br /></p>
	<div class="container">	
		<h2>JSTL(Java Standard Tag Library)</h2>
		<table class="table table-border table-hover">
			<tr class="table-dark text-dark">
				<th>라이브러리명</th>
				<th>주소(URL)</th>
				<th>접두어</th>
				<th>기본문법</th>
			</tr>
			<tr>
				<td>Core</td>
				<td>http://java.sun.com/jsp/jstl/core</td>
				<td>c</td>
				<td>< c : 태그명 ></td>
			</tr>
			<tr>
				<td>Formatting</td>
				<td>http://java.sun.com/jsp/jstl/fmt</td>
				<td>fmt</td>
				<td>< fmt : 태그명 ></td>
			</tr>
			<tr>
				<td>Function</td>
				<td>http://java.sun.com/jsp/jstl/function</td>
				<td>fn</td>
				<td>$ { fn : 태그명 }</td>		<!-- function만 EL표기법 -->
			</tr>
			<tr>
				<td>SQL</td>
				<td>http://java.sun.com/jsp/jstl/sql</td>
				<td>sql</td>
				<td>< sql : 태그명 ></td>
			</tr>
			<tr><td colspan='4' class='m-0 p-0'></td></tr>
		</table>
		<p><b>라이브러리 : 상단에 jsp지시자(taglib) 선언 후 사용</b></p>
		<br /><hr /><hr />
		<h3>Core라이브러리 : 변수제어(선언/할당/출력/제거), 제어문(조건문,반복문) - 제어문 안의 변수로 활용</h3>
		<pre>
			변수선언 : < c : set var="변수명" value="값" />
			변수출력 : < c : out value="$ {변수명} / 값" />
			변수제거 : < c : remove var="변수명" />			
		</pre>
		<p>사용 예 : 
			su1 변수를 선언 후 초기값으로 100을 할당<c:set var='su1' value='100' /><br />
			su1 변수의 값 출력 <c:out value="${su1}" /><br />
			su1 변수의 값에 100을 더하여 su2 변수에 할당 후 출력
			<c:set var="su2" value="${su1 + 100}" />
			<c:out value="${su2}" /><br />
			< c : out >은 el 변수로 대체 가능 : ${su2}
		</p>
		<hr />
		<h4>JSTL 제어문(core라이브러리 활용)</h4>
		<p>사용법 : < c : if test="$ {조건식}" > 조건식의 내용이 참일 때 수행 < /c : if ></p>
		<div>일반적인 비교연산 수행시 : 문자 비교</div>
		<div>숫자의 비교연산 수행 : 숫자로 변형(문자변수 + 0) 후 처리</div>
		<div>
			예제 : su3 = 100, su4 = 200 저장 후 두 값 비교<br />
			<c:set var="su3" value='100' />
			<c:set var="su4" value='20' />
			1-1. su3 == su4 : <c:if test="${su3 == su4}">su3와 su4는 같다.</c:if>
			1-1. su3 == su4 :	<c:if test="${su3 != su4}">su3와 su4는 다르다.</c:if><br />
			2-1. su3 > su4 :  <c:if test="${su3 > su4}">su3는 su4보다 크다.</c:if>
			2-2. su3 < su4 :  <c:if test="${su3 < su4}">su3는 su4보다 작다.</c:if>
								  	<%-- <c:if test="${su3 == su4}">su3는 su4보다 같다.</c:if> --%><br />
			3-1. su3 > su4 : <c:if test="${su3+0 > su4+0}">su3는 su4보다 크다.</c:if>
			3-2. su3 <= su4 : <c:if test="${su3+0 <= su4+0}">su3는 su4보다 작거나 같다.</c:if>
		</div>
		<div>
			예제 : URL에 jumsu를 입력 받아서 학점 구하기<br />
			<c:set var='jumsu' value='${param.jumsu}' />
			<c:if test="${jumsu+0 >= 60}"><c:set var="grade" value='D'/></c:if>
			<c:if test="${jumsu+0 >= 70}"><c:set var="grade" value='C'/></c:if>
			<c:if test="${jumsu+0 >= 80}"><c:set var="grade" value='B'/></c:if>
			<c:if test="${jumsu+0 >= 90}"><c:set var="grade" value='A'/></c:if>
			<c:if test="${jumsu+0 < 60}"><c:set var="grade" value='F'/></c:if>
			학점 : ${grade}
		</div>
		<hr />
		<h3>다중조건비교 : choose ~ when</h3>
		<pre>
			사용법 :
			< c : choose >
				< c : when test="조건식1" >수행할내용1< c : when >
				< c : when test="조건식2" >수행할내용2< c : when >
				< c : when test="조건식3" >수행할내용3< c : when >
				< c : when test="조건식4" >수행할내용4< c : when >
				~~~ ~~~ ~~~
				< c : otherwise >기타수행내용< / : otherwise >
			< / c : choose >
		</pre>
		<br />학점 : 
		<c:choose>
			<c:when test="${jumsu >= 90}">A</c:when>
			<c:when test="${jumsu >= 80}">B</c:when>
			<c:when test="${jumsu >= 70}">C</c:when>
			<c:when test="${jumsu >= 60}">D</c:when>
			<c:otherwise>F</c:otherwise>
		</c:choose>
		<br />작업끝
	</div>	<p><br /></p>
</body>
</html>