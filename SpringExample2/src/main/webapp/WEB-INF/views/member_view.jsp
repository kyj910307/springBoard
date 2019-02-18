<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="include/member_header.jsp" %>
<script>
	$(document).ready(function(){
		$("#btnUpdate").click(function(){
			if(confirm("수정하시겠습니까?")){
				document.form1.action = "${path}/member/update.do";
				document.form1.submit();
			}
		});
	});
	$(document).ready(function(){
		$("#btnDelete").click(function(){
			if(confirm("삭제하시겠습니까?")){
				document.form1.action = "${path}/member/delete.do";
				document.form1.submit();
			}
		});
	});
</script>
</head>
<body>
<%@ include file="include/member_menu.jsp" %>
<h2>회원등록</h2>
<form name="form1" method="post" action="${path}/member/insert.do">
<table border="1" width="700px">
	<tr>
		<th>아이디</th>
		<td><input name="userId" value="${dto.userId}" readonly="readonly"></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="userPw"></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input name="userName" value="${dto.userName}"></td>
	</tr>
	<tr>
		<th>회원가입일자</th>
		<td>
			<fmt:formatDate value="${dto.userRegdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>

	
	<tr>
		<th>회원정보수정일자</th>
		<td>
			<fmt:formatDate value="${dto.userUpdateddate}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	

	<tr>
		<th>이메일</th>
		<td><input name="userEmail"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" value="수정" id="btnUpdate">
			<input type="button" value="삭제" id="btnDelete">
			<div style="color:red;">${message}</div>
		</td>
	</tr>
</table>
</form>
</body>
</html>