<%@page import="com.yedam.notice.domain.NoticeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head><!--  -->

<body>
	<h1>공지사항 목록</h1>
	<c:set var="no" value="0"></c:set>
	<table class="table">
		<thead>
			<tr>
				<th>순번</th>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
		</thead>
		<c:forEach var="notice" items="${List }">
			<tr>
					
				<td><c:out value="${no=no+1 }"></c:out></td>
				<td>${notice.noticeId }</td>
				<td><a href="searchNotice.do?noticeId=${notice.noticeId }">${notice.noticeTitle }</a></td>
				<td>${notice.noticeWriter }</td>
				<td>${notice.hitCount }</td>
			</tr>
		</c:forEach>
	</table>
	<input type="button" value="글쓰기" onClick="location.href='insertNotice.do'">
</body>

</html>