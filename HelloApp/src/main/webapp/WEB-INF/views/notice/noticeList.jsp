<%@page import="com.yedam.notice.domain.NoticeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="my" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<my:forEach var="notice" items="${noticeList }">
		<p>${ notice }</p>
	</my:forEach>
	<input type="button" value="글쓰기" onClick="location.href='insertNotice.do'"><input type="button" value="글삭제" onClick="location.href='deleteNotice.do'">
</body>
</html>