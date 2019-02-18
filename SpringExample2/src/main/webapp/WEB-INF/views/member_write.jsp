<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="include/member_header.jsp" %>
</head>
<body>
<%@ include file="include/member_menu.jsp" %>
<h2>회원등록</h2>
<form name="form1" method="post" action="${path}/member/insert.do">
<table border="1" width="700px">
	<tr>
		<th>아이디</th>
		<td><input name="userId"></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="userPw"></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input name="userName"></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><input name="userEmail"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="등록">
			<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>
</body>
</html>