<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>

<body>
	<h1>공지사항 조회</h1>
	<table class="table" style="width:700px">
		<tr>
			<th>글 번호 </th>
			<td><input name="title" value="${notice.noticeId}" readonly></td>
		</tr>
		<tr>
			<th>제목 </th>
			<td><input name="title" value="${notice.noticeTitle}" readonly></td>
		</tr>
		<tr>
			<th>내용 </th>
			<td><textarea rows="3" cols="30" name="subject"  readonly>${notice.noticeSubject}</textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input name="writer" value="${notice.noticeWriter}" readonly></td>
		</tr>
		<tr>
			<th>첨부파일${fileType } </th>
			<td>
			<c:if test="${notice.attachFile !=null }">
				<c:choose>
					<c:when test="${fileType =='image'}">
					<image width="200px" src="images/${notice.attachFile}" >
					</c:when>
					<c:otherwise >
						<a href="image/${notice.attachFile }">${notice.attachFile }</a>
					</c:otherwise>
				</c:choose>
			</c:if>
			</td>
		</tr>
	</table> 
	<c:choose>
		<c:when test="${sesInfo.email == notice.noticeWriter || sesInfo.auth == 'Admin' }"    >																												
			<input type="button" value="수정" onClick="location.href='updateNotice.do?noticeId=${notice.noticeId}'">
		</c:when>
		<c:otherwise>
			<input disabled type="button" value="수정" onClick="location.href='updateNotice.do?noticeId=${notice.noticeId}'">
		</c:otherwise>
	</c:choose>
	   						<!-- searchListControl 에서 받은 현재페이지 넘버로 페이지이동 -->
	<input type="button" value="목록" onClick="location.href='noticeList.do?page=${pageNum}'">
</body>

</html>