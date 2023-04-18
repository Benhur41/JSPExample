<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="menu.jsp"></jsp:include>
<jsp:include page="nav.jsp"></jsp:include>
	<form action="addMember.do" method="POST">
		firstName : <input name="fname"><br>
		lastName : <input name="lname"><br>
		직무 : <input name="job"><br>
		입사일 : <input name="hire"><br>
		이메일 : <input name="email"><br>
		<input type="submit" value="추가">
	</form>
<jsp:include page="footer.jsp"></jsp:include>
