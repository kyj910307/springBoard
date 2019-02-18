<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/member_header.jsp" %>

<script>
	$(document).ready(function(){
		$("#btnwrite").click(function(){
			// 페이지 주소변경
			location.href= "${path}/board/write.do";
		});
	});
	// 원하는 페이지로 이동시 검색 조건,키워드값을 유지하기 위해
	function list(page){
		location.href="${path}/board/list.do?curPage="+page+"&searchOption-${map.searchOption}"+"&keyWord=${map.keyWord}"
	}
</script>

</head>
<body>
<%@ include file="../include/member_menu.jsp" %>
<h2>게시글 목록</h2>
<form name="form1" method="post" action="${path}/board/list.do">
	<select name="searchOption">
		<!-- 검색조건을 검색처리후 결과화면에 보여주기위해 c:out 출력태그를 사용, 삼항연산자 -->
		<option value="all"<c:out value="${map.searchOption == 'all'?'selected':''}"/>>
			제목+이름+내용
		</option>
		<option value="user_name" <c:out value="${map.searchOption == 'user_name'?'selected':''}"/>>
			작성자
		</option>
		<option value="content"<c:out value="${map.searchOption == 'content'?'selected':''}" />>
			내용
		</option>
		<option value="title"<c:out value="${map.searchOption == 'title'?'selected':''}"/>>
			제목
		</option>		
	</select>
	<input name="keyWord" value="${map.keyWord}">
	<input type="submit" value="조회">
	<c:if test="${sessionScope.userId != null}">
		<button type="button" id="btnwrite">글쓰기</button>
	</c:if>
</form>
${map.count}개의 게시물이 있습니다. 
<table border="1" width="600px">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>이름</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<c:forEach var="row" items="${map.list}">
		<tr>
			<td>${row.bno}</td>
			<!-- 게시글 상세보기 페이지로 이동시 게시글 목록페이지에있는 검색조건,키워드현재페이지값을 유지하기위해 -->
			<td><a href="${path}/board/view.do?bno=${row.bno}&curPage=${map.boardPager.curPage}&searchOption=${map.searchOption}&keyWord=${map.keyWord}">${row.title}
				<c:if test="${row.recnt > 0}">
					<span style="color:red;">(${row.recnt})</span>
				</c:if>
			</a></td>
			<td>${row.userName}</td>
			<td><fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd"/></td>
			<td>${row.viewcnt}</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="5">
			<!-- 처음페이지로 이동: 현재페이지가 1보다 크면 [처음] 하이퍼링크를 화면에 출력 -->
			<c:if test="${map.boardPager.curBlock > 1 }">
				<a href="javascript:list('1')">[처음]</a>
			</c:if>
			<c:if test="${map.boardPager.curBlock > 1}">
				<a href="javascript:list('${map.boardPager.prevPage}')">[이전]</a>		
			</c:if>
			<!-- 하나의 블럭에서 반복문수행 시작페이지부터끝페이지까지 -->
			<c:forEach var ="num" begin="${map.boardPager.blockBegin}" end="${map.boardPager.blockEnd}">
				<!-- 현재 페이지면 하이퍼링크 제거 -->
				<c:choose>
					<c:when test="${num == map.boardPager.curPage}">
						<span style="color:red">${num}</span>&nbsp;
					</c:when>
					<c:otherwise>
						<a href="javascript:list('${num}')">${num}</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- 다음페이지블록으로 이동 : 현재페이지블럭이 전체페이지블록보다 작거나 같으면 [다음]하이퍼링크를 화면에출력 -->
			<c:if test="${map.boardPager.curBlock <= map.boardPager.totBlock}">
				<a href="javascript:list('${map.boardPager.nextPage}')">[다음]</a>
			</c:if>
			<!-- 끝페이지로이동 : 현재페이지가 전체 페이지보다 작거나같으면 [끝] 하이퍼링크를 화면에 출력 -->
			<c:if test="${map.boardPager.curPage <= map.boardPager.totPage}">
				<a href="javascript:list('${map.boardPager.totPage}')">[끝]</a>
			</c:if>
		</td>
	</tr>
</table>
</body>
</html>