<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LIST</title>
<link rel="stylesheet" href="/resource/common.css" />
<script src="/resource/common.js" defer="defer"></script>
</head>
<body>
<header>
<nav class="nav-bar">
<h1><a href="../home/main">Article Manager</a></h1>
<div><a href="../article/getArticles">게시물 보기</a></div>
	<c:set var="loginMember" value="${loginMember}" />

	<c:choose>
		<c:when test="${loginMember.name eq null}">
			<ul>
				<li><a href="../member/loginForm">로그인</a></li>
				<li><a href="../member/joinForm">회원가입</a></li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul >
				<li><a href="../member/doLogout">로그아웃</a></li>
			</ul>
		</c:otherwise>
	</c:choose>
</nav>
</header>
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