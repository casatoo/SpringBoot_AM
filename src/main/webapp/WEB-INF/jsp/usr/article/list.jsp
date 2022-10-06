<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
body, ul, li {
	margin: 0;
	padding: 0;
	list-style: none;
}
a{
	text-decoration: none;
	color: inherit;
}
.nav-bar ul , li , h1{
display: flex;
align-items: center;
margin:0 50px;
}
.nav-bar{
display: flex;
justify-content: space-around;
}
.articleList {
display: flex;
justify-content: center;
}
table{
width: 600px;
}
table > tbody {
text-align: center;
}
</style>
<title>LIST</title>
</head>
<body>
<nav class="nav-bar">
<h1><a href="../home/main">Article Manager</a></h1>
	<h1>LIST</h1>
	<c:set var="loginMember" value="${loginMember}" />

	<c:choose>
		<c:when test="${loginMember.name eq null}">
			<ul>
				<li>접속하기</li>
				<li><a href="../member/loginForm">로그인</a></li>
				<li><a href="../member/joinForm">회원가입</a></li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul >
				<li>회원관리</li>
				<li><a href="../member/doLogout">로그아웃</a></li>
			</ul>
		</c:otherwise>
	</c:choose>
	</nav>
	<hr />
	<div class="articleList">
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>날짜</th>
				<th>제목</th>
				<th>작성자</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="article" items="${articles }">
				<tr>
					<td>${article.id}</td>
					<td>${article.regDate.substring(0,10)}</td>
					<td><a href="../article/getArticle?id=${article.id}">${article.title}</a></td>
					<td>${article.loginedId}</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>