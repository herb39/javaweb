<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>boardContent.jsp</title>
	<jsp:include page="/include/bs4.jsp" />
	<style>
		th {
			text-align: center;
			background-color: #eee;
		}
	</style>
	<script>
		'use strict';
		
		function goodCheck() {
			$.ajax({
				type	: "post",
				url		: "${ctp}/BoardGoodCheckAjax.bo",
				data	: {idx : ${vo.idx}},
				success	: function(res) {
					if (res == "0") alert("이미 '좋아요'를 누르셨습니다.");
					else location.reload();
				},
				error	: function() {
					alert("전송 오류");
				}
			});
		}
		
		function boardDelete() {
			let ans = confirm("게시글을 삭제하시겠습니까?");
			if (ans) location.href="${ctp}/BoardDelete.bo?idx=${vo.idx}&pag=${pag}&pageSize=${pageSize}&nickName=${vo.nickName}";
		}
		
		// 댓글쓰기 (aJax)
		function replyCheck() {
			let content = $("#content").val();
			if (content.trim() == "") {
				alert("댓글을 입력하세요.");
				$("#content").focus();
				return false;
			}
			let query = {
					boardIdx	: ${vo.idx},
					mid			: '${sMid}',
					nickName	: '${sNickName}',
					content		: content,
					hostIp		: '${pageContext.request.remoteAddr}'
			}
			
			$.ajax({
				type	: "post",
				url		: "${ctp}/BoardReplyInput.bo",
				data	: query,
				success	: function(res) {
					if (res == "1") {
						alert("댓글 작성 완료");
						location.reload();
					} else {
						alert("댓글 작성 실패");
					}
				},
				error	: function() {
					alert("전송 오류");
				}
				
			});
		}
		
		// 댓글삭제 (aJax)
		function replyDelete(replyIdx) {
			let ans = confirm("댓글을 삭제하시겠습니까?");
			if (!ans) return false;
			
			$.ajax({
				type	:"post",
				url		:"${ctp}/BoardReplyDelete.bo",
				data	:{replyIdx: replyIdx},
				success	:function(res) {
					if (res == "1"){
						alert("댓글 삭제 완료");
						location.reload();
					} else {
						alert("댓글 삭제 실패");
					}
				},
				error	:function() {
					alert("전송 오류");
				}
			});
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
	<p><br /></p>
	<div class="container">
		<h2 class="text-center">내 용</h2>
		<br />
		<table class="table table-borderless m-0 p-0">
			<tr>
				<td class="text-right">접속IP : ${vo.hostIp}</td>
			</tr>
		</table>
		<table class="table table-bordered">
			<tr>
				<th>작성자</th>
				<td>${vo.nickName}</td>
				<th>작성일</th>
				<td>${fn:substring(vo.wDate, 0, fn:length(vo.wDate)-2)}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">${vo.title}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${vo.email}</td>
				<th>조회수</th>
				<td>${vo.readNum}</td>
			</tr>
			<tr>
				<th>홈페이지</th>
				<td>${vo.homePage}</td>
				<th>좋아요</th>
				<td>
					${vo.good}
					(<a href="javascript:goodCheck()">
						<c:if test="${sSw == '1'}"><font color="#f00">❤</font></c:if>
						<c:if test="${sSw != '1'}"><font color="#000">❤</font></c:if>
					</a>️)
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3" style="height: 220px">${fn:replace(vo.content, newLine, "<br />")}</td>
			</tr>
			<tr>
				<td colspan="4" class="text-center">
					<c:if test="${flag == 'search'}">
						<input type="button" value="돌아가기" onclick="location.href='${ctp}/BoardSearch.bo?search=${search}&searchString=${searchString}&pag=${pag}&pageSize=${pageSize}';" class="btn btn-primary" />
					</c:if>
					<c:if test="${flag != 'search'}">
						<input type="button" value="돌아가기" onclick="location.href='${ctp}/BoardList.bo?pag=${pag}&pageSize=${pageSize}';" class="btn btn-primary" /> &nbsp;
						<c:if test="${sMid == vo.mid || sLevel == 0}">
							<input type="button" value="수정" onclick="location.href='${ctp}/BoardUpdate.bo?idx=${vo.idx}&pag=${pag}&pageSize=${pageSize}';" class="btn btn-warning" /> &nbsp;
							<input type="button" value="삭제" onclick="boardDelete()" class="btn btn-danger" />
						</c:if>
					</c:if>
				</td>
			</tr>
		</table>
		<c:if test="${flag != 'search'}">
			<!-- 이전글 / 다음 -->
			<table class="table table-borderless">
				<tr>
					<td>
						<c:if test="${nextVo.nextIdx != 0}">
							👆<a href="${ctp}/BoardContent.bo?idx=${nextVo.nextIdx}&pag=${pag}&pageSize=${pageSize}">다음글 : ${nextVo.nextTitle}</a><br />
						</c:if>
						<c:if test="${preVo.preIdx != 0}">
							👇<a href="${ctp}/BoardContent.bo?idx=${preVo.preIdx}&pag=${pag}&pageSize=${pageSize}">이전글 : ${preVo.preTitle}</a>
						</c:if>
					</td>
				</tr>
			</table>
		</c:if>
		<!-- 댓글 리스트 -->
		<div class="container">
			<table class="table table-hover text-left">
				<tr>
					<th> &nbsp;작성자</th>
					<th>내용</th>
					<th>작성일</th>
					<th>접속IP</th>
				</tr>
				<c:forEach var="replyVo" items="${replyVos}" varStatus="st">
					<tr>
						<td class="text-center">${replyVo.nickName}
							<c:if test="${sMid == replyVo.mid || sLevel == 0}">
								<a href="javascript:replyDelete(${replyVo.idx})" title="댓글삭제" id="deleteReply" style="color: red"><b>x</b></a>
							</c:if>
						</td>
						<td>${fn:replace(replyVo.content, newLine, "<br />")}</td>
						<td class="text-center">${fn:substring(replyVo.wDate, 0, 16)}</td>
						<td class="text-center">${replyVo.hostIp}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- 댓글 입력 -->
		<form name="replyForm">
			<table class="table table-center">
				<tr>
					<td style="width: 85%" class="text-left">
						내용
						<textarea rows="4" name="content" id="content" class="form-control"></textarea>
					</td>
					<td style="width: 15%">
						<br />
						<p>작성자 ${sNickName}</p>
						<input type="button" value="댓글쓰기" onclick="replyCheck()" class="btn btn-info btn-sm" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<p><br /></p>
	<jsp:include page="/include/footer.jsp" />
</body>
</html>