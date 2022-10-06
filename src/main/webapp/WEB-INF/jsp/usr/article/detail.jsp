<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DETAIL</title>
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
	<div>
	<div>게시글 번호: ${article.id}</div>
	<div>제목 : ${article.title}</div>
	<div>작성일 : ${article.regDate.substring(0,10)}</div>
	<div>작성자 : ${article.loginedId}</div>
	<div>내용 : ${article.body}</div>
	</div>
</body>
</html>