<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<table class="table" style="width: 700px">
		<tr>
			<th>글 번호</th>
			<td><input name="title" value="${notice.noticeId}" readonly></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input name="title" value="${notice.noticeTitle}" readonly></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="3" cols="30" name="subject" readonly>${notice.noticeSubject}</textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input name="writer" value="${notice.noticeWriter}" readonly></td>
		</tr>
		<tr style="display: none">
			<th>첨부파일${fileType }</th>
			<td><c:if test="${notice.attachFile !=null }">
					<c:choose>
						<c:when test="${fileType =='image'}">
							<image width="200px" src="images/${notice.attachFile}">
						</c:when>
						<c:otherwise>
							<a href="image/${notice.attachFile }">${notice.attachFile }</a>
						</c:otherwise>
					</c:choose>
				</c:if></td>
		</tr>
	</table>
	<c:choose>
		<c:when
			test="${sesInfo.email == notice.noticeWriter || sesInfo.auth == 'Admin' }">
			<input type="button" value="수정"
				onClick="location.href='updateNotice.do?noticeId=${notice.noticeId}'">
		</c:when>
		<c:otherwise>
			<input disabled type="button" value="수정"
				onClick="location.href='updateNotice.do?noticeId=${notice.noticeId}'">
		</c:otherwise>
	</c:choose>
	<!-- searchListControl 에서 받은 현재페이지 넘버로 페이지이동 -->
	<input type="button" value="목록"
		onClick="location.href='noticeList.do?page=${pageNum}'">

	<!-- 댓글작성 -->
	<div id='content'>
		<input type="text" id="reply"><span>${sesInfo.email }</span>
		<button type="submit" id="addBtn">등록</button>
	</div>

	<!-- 댓글정보  -->
	<table class="table">
		<thead>
			<tr>
				<th>댓글번호</th>
				<th>글내용</th>
				<th>작성자</th>
				<th>삭제</th>
				<th>수정</th>
			</tr>
		</thead>
		<tbody id="tlist">

		</tbody>
	</table>
	<table style="display: none;">
		<tbody>
			<tr class="template">
				<td>10</td>
				<td><input type="text" class="reply"></td>
				<td>user01</td>
				<td><button>수정</button></td>
			</tr>
		</tbody>
	</table>

	<script>
	let showFields = ['replyId', 'reply', 'replyWriter']
	let xhtp = new XMLHttpRequest(); //Ajax 호출.
	xhtp.open('get', 'replyList.do?nid=${notice.noticeId}');
	xhtp.send();
	xhtp.onload = function () {
		//console.log(xhtp.response);
		let tlist = document.querySelector('#tlist');
		//목록생성.
		let data = JSON.parse(xhtp.response);
		for (let reply of data) {
			console.log(reply);
			let tr = makeTrFunc(reply);
			tlist.append(tr);
		}
	}

	// tr 생성해주는 함수.
	function makeTrFunc(reply = {}) {
		let tr = document.createElement('tr');
		tr.id = reply.replyId; // tr 속성추가: 댓글번호

		// this 1) Object 안에서 사용되면 object자체를 가리킴.
		//      let obj = {name: "hong", age: 20, showInfo: function() { this.age + this.name }}
		//      2) function 선언안에서 this는 window 전역객체. <-> 지역
		//      function add() { console.log(this) }
		//      3) event 안에서 사용되는 this는 이벤트 받는 대상.
		//      document.getElementById('tlist').addEventListener('click',function() {console.log(this)})
		// tr 클릭이벤트.
		tr.addEventListener('dblclick', function (e) {
			let writer = this.children[2].innerText;
			
			console.log(writer, '${sesInfo.email }');
			if(writer != '${sesInfo.email}') {
				alert('권한이 없습니다.');
				return;
			}
			
			console.log(this);
			let template = document.querySelector('.template').cloneNode(true);
			console.log(template);
			//template.children[0].innerText = reply.replyId;
			//template.children[1].children[0].value = reply.reply;
			//template.children[2].innerText = reply.replyWriter;
			template.querySelector('td:nth-of-type(1)').innerText = reply.replyId;
			template.querySelector('td:nth-of-type(2)>input').value = reply.reply;
			template.querySelector('td:nth-of-type(3)').innerText = reply.replyWriter;
			template.querySelector('button').addEventListener('click', function (e) {
				// Ajax 호출.
				let replyId = reply.replyId;
				let replyCon = this.parentElement.parentElement.children[1].children[0].value;
				console.log(replyId, replyCon);
				
				let xhtp = new XMLHttpRequest();
				xhtp.open('post', 'modifyReply.do');
				xhtp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				xhtp.send('rid='+replyId+'&reply='+replyCon);
				xhtp.onload = function() {
					let result = JSON.parse(xhtp.response);
					if (result.retCode == 'Success') {
						// 화면변경.
						alert('성공.');
						console.log(result.data);
						tr = makeTrFunc(result.data);
						document.getElementById('tlist').replaceChild(tr, template);
					} else if (result.retCode == 'Fail') {
						alert('처리중 에러.');
					} else {
						alert('알수 없는 반환값.');
					}
				}
			});
			// 화면전환.
			document.getElementById('tlist').replaceChild(template, tr);
		})
		// td 생성.
		for (let prop of showFields) {
			let td = document.createElement('td');
			td.innerText = reply[prop];
			tr.append(td);
		}
		//삭제버튼.
		let btn = document.createElement('button');
		btn.addEventListener('click', function (e) {
			let writer = btn.parentElement.previousElementSibling.innerText;

			console.log(writer, '${sesInfo.email }');
			if (writer != '${sesInfo.email}') {
				alert('권한이 없습니다.');
				return;
			}


			console.log(btn.parentElement.parentElement);
			let rid = btn.parentElement.parentElement.id;
			// db에서 삭제 후... 화면에서 삭제.
			let xhtp = new XMLHttpRequest();
			xhtp.open('post', 'removeReply.do');
			xhtp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xhtp.send('rid=' + rid); // 요청방식 post일 경우에 parameter를 send() 포함.

			xhtp.onload = function () {
				let result = JSON.parse(xhtp.response);
				if (result.retCode == 'Success') {
					// 화면에서 지우기.
					btn.parentElement.parentElement.remove(); // 제거.
					alert('삭제 완료');
				} else if (result.retCode == 'Fail') {
					alert('처리중 에러발생.');
				} else {
					alert('알수 없는 결과값입니다.');
				}
			}
		});
		btn.innerText = '삭제';
		let td = document.createElement('td');
		td.append(btn);
		tr.append(td);

		return tr; //생성한 tr 을 반환.
	}

	// 등록이벤트...
	document.querySelector("#addBtn").addEventListener('click', addReplyFnc);

	function addReplyFnc(e) {
		// 로그인 여부를 체크해서 로그인 정보가 없으면 로그인화면으로 이동하기.
		let id = document.querySelector('#content span').innerText;
		if (id == null || id == '') {
			alert("로그인 후에 처리하세요.");
			location.href = 'loginForm.do';
			return;
		}
		let replySub = document.querySelector('#reply').innerText;
		if (replySub == null || replySub == '') {
			alert("댓글 내용을 입력하세요.");
			document.getElementById("reply").focus();
			return;
		}
		console.log('click', e.target);
		console.log('reply', document.querySelector("#reply").value);
		console.log('id', "${sesInfo.email }");
		let reply = document.querySelector("#reply").value;

		// Ajax 호출.  post 형태
		let xhtp = new XMLHttpRequest();
		xhtp.open('post', 'addReply.do');
		xhtp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhtp.send('id=${id }&reply=' + reply + "&notice_id=${noticeInfo.noticeId}");
		xhtp.onload = function () {
			console.log(xhtp.response);
			let result = JSON.parse(xhtp.response);
			if (result.retCode == 'Success') {
				// 값을 활용해서 tr 생성.
				let tr = makeTrFunc(result.data);
				tlist.append(tr);

				// 입력값 초기화하기.
				document.getElementById("reply").value = '';
				document.getElementById("reply").focus();
			} else if (result.retCode == 'Fail') {
				alert('처리중 에러.');
			}
		}
	}
</script>
</body>

</html>