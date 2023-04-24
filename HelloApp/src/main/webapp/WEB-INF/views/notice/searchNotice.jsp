<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		#content {
			padding: 15px auto;
		}
	</style>
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
			<td><textarea rows="3" cols="30" name="subject" readonly>${notice.noticeSubject}</textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input name="writer" value="${notice.noticeWriter}" readonly></td>
		</tr>
		<tr style="display:none">
			<th>첨부파일${fileType } </th>
			<td>
				<c:if test="${notice.attachFile !=null }">
					<c:choose>
						<c:when test="${fileType =='image'}">
							<image width="200px" src="images/${notice.attachFile}">
						</c:when>
						<c:otherwise>
							<a href="image/${notice.attachFile }">${notice.attachFile }</a>
						</c:otherwise>
					</c:choose>
				</c:if>
			</td>
		</tr>
	</table>
	<c:choose>
		<c:when test="${sesInfo.email == notice.noticeWriter || sesInfo.auth == 'Admin' }">
			<input type="button" value="수정" onClick="location.href='updateNotice.do?noticeId=${notice.noticeId}'">
		</c:when>
		<c:otherwise>
			<input disabled type="button" value="수정" onClick="location.href='updateNotice.do?noticeId=${notice.noticeId}'">
		</c:otherwise>
	</c:choose>
	<!-- searchListControl 에서 받은 현재페이지 넘버로 페이지이동 -->
	<input type="button" value="목록" onClick="location.href='noticeList.do?page=${pageNum}'">

	<!-- 댓글작성 -->
	<div id='content'>
		<input type="text" id="reply"><span>${sesInfo.email }</span><button type="submit" id="addBtn"> 등록</button>
	</div>

	<!-- 댓글정보  -->
	<table class="table">
		<thead>
			<tr>
				<th>댓글번호 </th>
				<th>작성자</th>
				<th>글내용</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody id="tlist">

		</tbody>
	</table>


	<script>
		let showFields = ['replyId', 'reply', 'replyWriter'];
		let xhtp = new XMLHttpRequest();
		xhtp.open('get', 'replyList.do?nid=${notice.noticeId}');
		xhtp.send();
		xhtp.onload = function () {
			let tlist = document.querySelector('#tlist');
			let result = JSON.parse(xhtp.response); // json -> object

			for (let reply of result) {
				let tr = makeTrFunc(reply.data);
				tlist.append(tr);
			}
		}

		// tr 생성해주는 함수 . 
		function makeTrFunc(reply = {}) {
			let tr = document.createElement('tr');
			for (let prop of showFields) {
				let td = document.createElement('td');
				td.innerText = reply[prop];
				tr.append(td);
			}
			//삭제 버튼
			let btn = document.createElement('button');
			btn.addEventListener('click',function (e){
				let rid = btn.parentElement.parentElement.children[0].innerHTML;
				// db 에서 삭제 후 ,, 화면에서 삭제 
				let xhtp = new XMLHttpRequest();
				xhtp.open('post','removeReply.do?noticeId=${notice.noticeId}');
				xhtp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
				xhtp.send('rid='+rid);
				xhtp.onload = function (){
					let result = JSON.parse(xhtp.response);
					
					if(result.retCode == 'Success'){
						//화면에서 지우기
						btn.parentElement.parentElement.remove();
					}else if(result.retCode == 'Fail'){
						alert('처리중 에러발생')
					}else{
						alert('알수 없는 결과값입니다')
					}
				}
			})
			btn.innerText = '삭제';
			let td = document.createElement('td');
			td.append(btn);
			tr.append(td);
			return tr; //생성한 tr을 반환.
		}
		//등록시 이벤트
		document.querySelector('#addBtn').addEventListener('click', addReply);


		function addReply(e) {
			console.log("click", e.target);
			console.log('reply', document.querySelector('#reply').value);

			//로그인 여부를 체크해서 로그인 정보가 없으면 로그인화면으로 이동하기
			let id = document.querySelector('#content span').innerText;
			if (id == null || id == '') {
				alert("로그인 후에 처리하세요");
				location.href = 'loginForm.do';
				return;
			}

			let reply = document.querySelector('#reply').value;
			//Ajax 호출
			let xhtp = new XMLHttpRequest();
			xhtp.open('post', 'addReply.do');
			xhtp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xhtp.send('id=${sesInfo.email}&reply=' + reply + "&noticeId=${notice.noticeId}");
			xhtp.onload = function () {
				console.log(xhtp.response); //{"retCode":"Success"}
				let result = JSON.parse(xhtp.response);
				if (result.retCode == 'Success') {
					//값을 활용해서 tr 생성.
					let tr = makeTrFunc(result.data);
					tlist.append(tr);

					//입력값 초기화하기
					document.getElementById("reply").value = '';
					document.getElementById("reply").focus();
				} else if (result.retCode == 'Fail') {
					alert('처리중 에러');
				}
			}
		}
	</script>
</body>

</html>