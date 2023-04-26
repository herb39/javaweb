<!-- t2_SessionDelete.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	session.invalidate();
%>
<script>
  alert("세션 삭제 완료 / 세션값 : ${sMid}");
  location.href = "t2_SessionMain.jsp";
</script>