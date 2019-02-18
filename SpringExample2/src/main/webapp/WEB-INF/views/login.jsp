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
		$("#btnLogin").click(function(){
			// name[tag].val() 입력된값
			// name[tag].val("") 값을변경
			var userId = $("#userId").val();
			var userPw = $("#userPw").val();
			if(userId == ""){
				alert("아이디를 입력하세요");
				$("#userId").focus(); // 해당입력창으로 포커스 이동
				
				return
			}
			if(userPw == ""){
				alert("비밀번호를 입력하세요");
				$("#userPw").focus();
				
				return
			}
			// 폼내부의 데이터를 전송할 주소
			document.form1.action="${path}/member/loginCheck.do"
			
			// 전송
			document.form1.submit();
		});
	});
</script>
</head>
<body>
<%@ include file="include/member_menu.jsp" %>
<h2>Login</h2>
<form name="form1" method="post">
<table border="1" width="400px">
	<tr>
		<th>아이디</th>
		<td><input name="userId" id="userId"></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="userPw" id="userPw"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button type="button" id="btnLogin">로그인</button>
			<c:if test="${msg == 'fail'}">
				<div style="color: red">
					<p>아이디나 비밀번호가 다릅니다.</p>
				</div>
			</c:if>
			<c:if test="${msg == 'logout'}">
				<div style="color:blue">
					<p>로그아웃되었습니다.</p>
				</div>
			</c:if>
		</td>
	</tr>
</table>
</form>
</body>
</html>