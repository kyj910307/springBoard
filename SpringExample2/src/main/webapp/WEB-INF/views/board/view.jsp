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
		
		//listRply2(); // json 리턴방식
		
		// 목록버튼 클릭이벤트 : 버튼클릭시 상세보기 화면에 있던 페이지, 검색옵션,키워드값을 가지고 목록으로 이동
		$("#btnList").click(function(){
			location.href="${path}/board/list.do";
			//${path}/board/list.do?curPage=${curPage}&searchOption=${searchOption}&keyWord=${keyWord}";
		});
	
		$("#btnDelete").click(function(){
			if(confirm("삭제하시겠습니까?")){
				document.form1.action="${path}/board/delete.do"
				// 폼에 입력한 데이터를 서버로 전송
				document.form1.submit();
			}	
		});
		
		$("#btnUpdate").click(function(){
			var title = $("#title").val();
			var content = $("#content").val();
			var writer = $("#writer").val();
			if(title == ""){
				aler("제목을 입력하세요");
				document.form1.title.focus();
				
				return;
			}
			
			if(content == ""){
				alert("내용을 입력하세요");
				document.form1.content.focus();
				
				return;
			}
			
			/*if(writer == ""){
				alert("작성자를 입력하세요");
				documen.form1.writer.focus();
			}*/
			document.form1.action="${path}/board/update.do"
			// 폼에 입력한 데이터를 서버로 전송
			document.form1.submit();
		});
		// 댓글 입력 버튼
		$("#btnReply").click(function(){
			reply();
		});
	});
	
	// 댓글 쓰기 버튼 이벤트
	function reply(){
		var replytext = $("#replytext").val();
		var bno = "${dto.bno}"
		// 비밀댓글 체크여부
		var secretReply = "n"; // default 값 N 
		// 태그.is(:속성) 체크여부 true/ false 체크박스 값을 받기위해
		if($("#secretReply").is(":checked")){
			secretReply = "y";
		}
		// 비밀댓글 파라미터 추가
		var param = "replytext="+replytext+"&bno="+bno+"&secretReply="+secretReply;
		$.ajax({
			type : "post",
			url : "${path}/reply/insert.do",
			data : param,
			success: function(){
				alert("댓글이 등록되었습니다.");
				listReply("1");
			}
		});
	}
		listReply("1");
		// 댓글목록
		function listReply(num){
			$.ajax({
				type   : "get",
				url    : "${path}/reply/list.do?bno=${dto.bno}&curPage="+num,
				success: function(result){
					$("#listReply").html(result);
				},error: function(result){
					console.log("listReply() Error"+result);
				}
			});
		} // listReply() end
		
		/*function listRply2(){
			$.ajax({
				type        : "get",
				contentType : "application/json",
				url         : "${path}/reply/listJson.do?bno=${dto.bno}",
				success     : function(result){
					console.log(result);
					var output = "<table>";
					for(var i in result){
						output += "<tr>";
						output += "<td>"+result[i].userName;
						output += "("+changeDate(result[i].regdate)+")<br>";
						output += result[i].replytext+"</td>";
						output += "</tr>";
					}
					output += "</table>";
					$("#listReply").html(output);
				},
				error: function(result){
					console.log("에러");
					}
			});
		}// listJson()end
		*/
		
		// 댓글수정화면 생성
		function showReplyModify(rno){
			$.ajax({
				type    : "get",
				url     : "${path}/reply/detail/"+ rno,
				success : function(result){
					$("#modifyReply").html(result);
					// 태그.css("속성","값")
					$("#modifyReply").css("visibility","visible");
				},error: function(result){
					console.log("showReplyModify() Error");
				}
			});
		}
		// changeDate 날짜 변환 함수
		function changeDate(date){
			date    = new Date(parseInt(date));
			year    = date.getFullYear();
			month   = date.getMonth();
			day     = date.getDate();
			hour    = date.getHours();
			minute  = date.getMinutes();
			second  = date.getSeconds();
			strDate = year +"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
			
			return strDate;
		}

	</script>
</head>
<style>
	#modifyReply{
		width: 600px;
		height: 130px;
		background-color: gray;
		padding: 10px;
		z-index : 10;
		visibility: hidden;
	}
</style>
<body>
<%@ include file="../include/member_menu.jsp" %>
<h2>회원등록</h2>
<form name="form1" method="post">
<table border="1" width="700px">
	<tr>
		<th>작성일자</th>
		<td>
			<fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${dto.viewcnt}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td><input name="title" id="title" size="80" value="${dto.title }" placeholder="제목을 입력해주세요"></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name="content" id="content" rows="4" cols="80"  placeholder="내용을입력해주세요">${dto.content }</textarea></td>
	</tr>
	<tr>
		<th>이름</th>
		<!-- <td><input name="writer" id="writer" value="${dto.writer}" placeholder="이름을입력해주세요"></td> -->
		<td>${dto.userName}</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<!-- 게시물 번호를 hidden 으로 처리 -->
		<input type="hidden" name="bno" value="${dto.bno}">
		<!-- 본인이 작성한 게시물만 수정,삭제 가능 하게 -->
		<c:if test= "${ sessionScope.userId == dto.writer}">
			<button type="button" id="btnUpdate">수정</button>
			<button type="button" id="btnDelete">삭제</button>
			<button type="reset">취소</button>
		</c:if>	
			<button type="button" id="btnList">목록</button>
		</td>
	</tr>
</table>
</form>

<div style="width:650px; text-align: center">
	<c:if test="${sessionScope.userId != null }">
	<textarea rows="5" cols="80" id="replytext"></textarea>
	<br>
	<!-- 비밀댓글 -->
	<input type="checkbox" id="secretReply">비밀글
	<button type="button" id="btnReply">댓글 작성</button>
	</c:if>
</div>
<div id="listReply"></div>
</body>
</html>