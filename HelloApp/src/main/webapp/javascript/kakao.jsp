<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
		<tr>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<td id="1"><p></p></td>
		</tr>
		<tr>
			<td id="2"></td>
		</tr>
		<tr>
			<td id="3"></td>
		</tr>
		<tr>
			<td id="4"></td>
		</tr>
</table>


	<script src="https://code.jquery.com/jquery-3.6.4.js"
		integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E="
		crossorigin="anonymous"></script>
	<script>
		$(function (){$.ajax({
			method : "GET",
			url : "https://dapi.kakao.com/v3/search/book?target=title",
			data : {
				target:"title",
				size:50,
				query:"혼자",
				page:2
			},
			headers:{Authorization: "KakaoAK a586331ea9ef27d3ebe02e01fd1e2619"},
		}).done(function(msg) {
			console.log(msg);
			$("#1").append("<p>"+msg.documents[1].title+"</p>") 
			$("#2").append("<img src='"+msg.documents[1].thumbnail+"'>")
			$("#3").append("<p>"+msg.documents[1].contents+"</p>")
			$("#4").append("<p>"+msg.documents[1].authors+"</p>")
		})});
	</script>
</body>
</html>