<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/member_header.jsp" %>
</head>
<body>
	<table style="width:700px">
		<c:forEach var ="row" items="${list}">
			<tr>
				<td>
					${row.userName}(<fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>)
					<br>
					${row.replytext}
					<br>
					<c:if test="${sessionScope.userId == row.replyer}">
						<input type="button" id="btnModify" value="수정" onclick="showReplyModify('${row.rno}')">
					</c:if>
				</td>
			</tr>
		</c:forEach>
		<!--  페이징처리 -->
		<tr>
			<td>
				<!-- 현재페이지 블럭이 1보다 크면 처음으로 이동 -->
				<c:if test="${replyPager.curBlock > 1}">
					<a href="javascript:listReply('1')">[처음]</a>
				</c:if>
				<!-- 페이지블럭이 1보다 크면 이전페이지 블록으로 이동 -->
				<c:if test="${replyPager.curBlock > 1}">
					<a href="javascript:listReply('${replyPager.prevPage}')">[이전]</a>
				</c:if>
				<!-- 페이지블럭 처음부터 마지막 블럭까지 1씩 증가하는 페이지 출력 -->
				<c:forEach var="num" begin="${replyPager.blockBegin}" end="${replyPager.blockEnd}">
					<c:choose>
						<c:when test="${num == replyPager.curPage}">
							${num}&nbsp;
						</c:when>
						<c:otherwise>
							<a href="javascript:listReply('${num}')">${num}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- 현재페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 다음페이지로 이동 -->
				<c:if test="${replyPager.curBlock <= replyPager.totBlock }">
					<a href="javascript:listReply('${replyPager.nextPage}')">[다음]</a>
				</c:if>
				<!-- 현재페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 끝페이지로 이동 -->
				<c:if test="${replyPager.curBlock <= replyPager.totBlock }">
					<a href="javascript:listReply('${replyPager.totPage}')">[끝]</a>
				</c:if>
			</td>
		</tr>
	</table>
	<!-- 댓글수정 영역 -->
	<div id="modifyReply"></div>
</body>
</html>