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
	<jsp:include page="../main.jsp"></jsp:include>
	<my:if test="${noticeList == null }">
		<p>${noticeList }</p>
	</my:if>
	
	<my:forEach begin="1" end="6" step="1" var="i">
		<p>${i}</p>
	</my:forEach>
	<my:forEach var="notice" items="${noticeList }">
		<p>${ notice }</p>
	</my:forEach>
</body>
</html>