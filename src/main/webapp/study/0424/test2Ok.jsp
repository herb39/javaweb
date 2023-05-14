<!-- 디렉티브 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 스크립틀릿 대신 jstl 사용 -->
<!-- 앞에서 전송된 값 vo객체에 담기 -->
<!-- jsp에서 객체를 사용하려면 해당 객체를 jsp액션태그(useBean)로 생성해야 함 -->
	<jsp:useBean id="vo" class="study.t0424.Test1VO" />

<!-- servlet에서는 getter() / setter() 사용해서 값을 불러오거나 저장 -->
<!-- jsp에서는 getProperty / setProperty 사용-->

<jsp:setProperty property="*" name="vo"/>

<html>
<head>
  <meta charset="UTF-8">
  <title>test2Ok.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
</head>
<body>
<p><br/></p>
<div class="container">
  <h3>처리된 자료를 view에 출력시켜본다.</h3>
  <div>
    <table class="table table-bordered">
      <tr>
        <th>성명</th>
        <td><%=vo.getName() %></td>
      </tr>
      <tr>
        <th>나이</th>
        <td><%=vo.getAge() %></td>
      </tr>
      <tr>
        <th>성별</th>
        <td><%=vo.getGender() %></td>
      </tr>
      <tr>
        <th>직업</th>
        <td><%=vo.getJob() %></td>
      </tr>
      <tr>
        <th>주소</th>
        <td><%=vo.getAddress() %></td>
      </tr>
    </table>
    <p>
      <a href="test2.jsp" class="btn btn-warning">돌아가기</a>
    </p>
  </div>
</div>
<p><br/></p>
</body>
</html>