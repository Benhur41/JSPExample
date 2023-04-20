<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>공지사항 추가</h1>
	<form action="insertNotice.do" method="POST" enctype="multipart/form-data">
		<table class="table" style="width:700px">
			<tr><th>작성자</th><td><input name="writer" ></td></tr>
			<tr><th>제목 </th><td><input name="title" ></td></tr>
			<tr><th>내용 </th><td><textarea rows="3" cols="30" name="subject" ></textarea></td></tr>
			<tr><th>파일 </th><td><input type="file" name="file" ></td></tr>
			<tr><td><input type="submit" value="추가"></td></tr>
		</table>
	</form>
</body>
</html>