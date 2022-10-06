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
<title>HOME</title>
</head>
<body>
<c:set var="resultRd" value="${resultRd}" />
<c:if test="${resultRd.resultCode eq 'S-1'}">
	<script>
	alert("${resultRd.msg}");
	</script>	
</c:if>
<nav class="nav-bar">
<h1><a href="../home/main">Article Manager</a></h1>
<h1><a href="../article/getArticles">게시물 보기</a></h1>
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
</body>
</html>