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
</style>
<title>detail</title>
</head>
<body>

<nav class="nav-bar">
<h1>Detail</h1>
	<c:set var="loginMember" value="${loginMember}" />

	<c:choose>
		<c:when test="${loginMember.name eq null}">
			<ul>
				<li>접속하기</li>
				<li><a href="../member/login">로그인</a></li>
				<li><a href="../member/join">회원가입</a></li>
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
	<div>
	<div>게시글 번호: ${article.id}</div>
	<div>제목 : ${article.title}</div>
	<div>작성일 : ${article.regDate.substring(0,10)}</div>
	<div>작성자 : ${article.loginedId}</div>
	</div>
	<div>내용 : ${article.body}</div>
</body>
</html>