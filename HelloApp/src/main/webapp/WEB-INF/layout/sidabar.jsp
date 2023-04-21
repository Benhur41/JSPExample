<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="border-end bg-white" id="sidebar-wrapper">
				<c:choose>
					<c:when test="${sesInfo.email !=null }">
	                	<div class="sidebar-heading border-bottom bg-light"><p>${sesInfo.name }님 환영합니다</p></div>
	                </c:when>
	                <c:otherwise>
	                	<div class="sidebar-heading border-bottom bg-light"><p>로그인 해주세요.</p></div>
	                </c:otherwise>
                </c:choose>
                <div class="list-group list-group-flush">
                    <c:choose>
	                    <c:when test="${sesInfo.email ==null }">
	                    	<a class="list-group-item list-group-item-action list-group-item-light p-3" href="loginForm.do">로그인</a>
	                    </c:when>
	                    <c:otherwise>
		                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="noticeList.do">공지사항 목록</a>
		                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="insertNotice.do">공지사항 등록</a>
	                   	 	<a class="list-group-item list-group-item-action list-group-item-light p-3" href="logout.do">로그아웃</a>
	                    </c:otherwise>
                    </c:choose>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Profile</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Status</a>
                </div>
            </div>