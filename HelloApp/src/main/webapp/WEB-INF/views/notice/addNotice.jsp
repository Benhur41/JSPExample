<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="insertNotice.do" method="POST">
		<label for="writer">작성자</label><input name="writer" id="writer">
		<label for="title">제목</label><input name="title" id="title">
		<label for="subject">내용</label><input name="subject" id="subject">
		<input type="submit" value="추가">
	</form>
</body>
</html>