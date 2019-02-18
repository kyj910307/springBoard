<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/member_header.jsp" %>
<script>
	
	$("#btnReplyUpdate").click(function(){
		var detailReplytext = $("#detailReplytext").val();
		$.ajax({
			type    : "put",
			url     : "${path}/reply/update/${replyVO.rno}",
			headers : {
				"Content-Type":"application/json"
			}, // 기본값 text/html 을 json 으로 변경
			data    : JSON.stringify({
				replytext : detailReplytext
			}),
			dataType : "text",
			success  : function(result){
				if(result == "success"){
					$("modifyReply").css("visibility","hidden");
					// 댓글 목록 갱신
					listReply("1");
				}
			}
		}); // ajax end
	});
	
	// reply close Btn()
	$("#btnReplyColse").click(function(){
		$("#modifyReply").css("visibility","hidden");
	});
		
	</script>
</head>
<body>
<table border="1" width="700px">
	<tr>
		<th>댓글번호</th>
		<td>${replyVO.rno}</td>
	</tr>
	<tr>
		<td>
			<textarea id="detailReplytext" rows="5" cols="80">${replyVO.replytext}</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<!-- 본인이 작성한 댓글만 수정,삭제 가능 하게 -->
		<c:if test= "${ sessionScope.userId == replyVO.replyer}">
			<button type="button" id="btnReplyUpdate">수정</button>
			<button type="button" id="btnReplyDelete">삭제</button>
			<button type="reset">취소</button>
		</c:if>	
			<button type="button" id="btnReplyColse">닫기</button>
		</td>
	</tr>
</table>

</body>
</html>