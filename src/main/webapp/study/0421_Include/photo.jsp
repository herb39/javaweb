<!-- photo.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h2>이곳은 갤러리입니다.</h2>
<hr />
<%	for(int i=1; i<=6; i++) {%>
			<img src="<%=request.getContextPath() %>/images/<%=i %>.jpg" width='200px'>
<%	} %>
<hr />
<p>알랄랄 시간 되세요.</p>