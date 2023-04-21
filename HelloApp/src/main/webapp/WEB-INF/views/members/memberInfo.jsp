<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table">
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email" value="${sesInfo.email }" readonly></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pass" required value="${sesInfo.password }" readonly></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" required value="${sesInfo.name}" readonly></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="phone" required value="${sesInfo.phone }" readonly></td>
			</tr>
	</table>
</body>
</html>