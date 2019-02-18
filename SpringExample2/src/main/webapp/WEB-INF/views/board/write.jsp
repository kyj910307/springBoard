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
		$("#btnSave").click(function(){
			var title = $("#title").val();
			var content = $("#content").val();
			var writer = $("#writer").val();
			alert("1");
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
			
			// 폼에 입력한 데이터를 서버로 전송
			document.form1.submit();
		});
	});
	</script>
</head>
<body>
<%@ include file="../include/member_menu.jsp" %>
<h2>회원등록</h2>
<form name="form1" method="post" action="${path}/board/insert.do">
<table border="1" width="700px">
	<tr>
		<th>제목</th>
		<td><input name="title" id="title" size="80" placeholder="제목을 입력해주세요"></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name="content" id="content" rows="4" cols="80" placeholder="내용을입력해주세요"></textarea></td>
	</tr>
	<!-- <tr>
		<th>이름</th>
		<td><input name="writer" id="writer" placeholder="이름을입력해주세요"></td>
	</tr> -->
	<tr>
		<td colspan="2" align="center">
			<button type="button" id="btnSave">등록</button>
			<button type="reset">취소</button>
		</td>
	</tr>
</table>
</form>
</body>
</html>