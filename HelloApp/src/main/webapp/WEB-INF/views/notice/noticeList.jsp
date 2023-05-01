<%@page import="com.yedam.notice.domain.NoticeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.center {
	text-align: center;
}

.pagination {
	display: inline-block;
}

.pagination a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
	transition: background-color .3s;
	border: 1px solid #ddd;
	margin: 0 4px;
}

.pagination a.active {
	background-color: #4CAF50;
	color: white;
	border: 1px solid #4CAF50;
}

.pagination a:hover:not(.active) {
	background-color: #ddd;
}
</style>
<body>
<p>${pageInfo }</p>
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
			<td><a href="searchNotice.do?noticeId=${notice.noticeId }&&pageNum=${pageInfo.pageNum}">${notice.noticeTitle }</a></td>
			<td>${notice.noticeWriter }</td>
			<td>${notice.hitCount }</td>
		</tr>
	</c:forEach>
</table>
<hr>
<div class="center">
	<div class="pagination">
		<c:if test="${pageInfo.prev }"> <!-- startpage 가 1보다 크다면 (= 이전 페이지 존재한다면) -->
			<a href="noticeList.do?page=${pageInfo.startPage-1 }">previous</a><!-- 해당 페이지 첫번째 숫자보다 1 낮은 페이지로 이동 -->
		</c:if>
		<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
			<a class="${i==pageInfo.pageNum ? 'active' : '' }" href="noticeList.do?page=${i }">${i } </a>
		</c:forEach>
		<c:if test="${pageInfo.next }">
			<a href="noticeList.do?page=${pageInfo.endPage+1 }">next</a>
		</c:if>
	</div>
</div>
</body>

</html>