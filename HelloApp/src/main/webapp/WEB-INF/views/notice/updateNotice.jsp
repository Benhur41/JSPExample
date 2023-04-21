<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>공지사항 수정</h1>
	<form action="updateNotice.do" method="POST">
	<table class="table" style="width:700px">
		<tr>
			<th>글 번호 </th>
			<td><input name="noticeId" value="${notice.noticeId}" readonly></td>
		</tr>
		<tr>
			<th>제목 </th>
			<td><input name="title" value="${notice.noticeTitle}" ></td>
		</tr>
		<tr>
			<th>내용 </th>
			<td><textarea rows="3" cols="30" name="subject" >${notice.noticeSubject}</textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input name="writer" value="${notice.noticeWriter}" readonly></td>
		</tr>
		<tr>
			<th>첨부파일 </th>
			<td>
				<c:choose>
					<c:when test="${notice.attachFile != null && notice.attachFile != ' '}">
					<image width="200px" src="images/${notice.attachFile}" >
					</c:when>
				</c:choose>
			</td>
		</tr>
	</table>
	<input type="submit" value="수정">
	<input type="button" value="삭제" onClick="location.href='deleteNotice.do?noticeId=${notice.noticeId}'">
	</form>
</body>
</html>