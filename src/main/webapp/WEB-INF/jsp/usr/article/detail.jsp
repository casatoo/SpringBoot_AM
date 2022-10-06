<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE DETAIL"/>
<%@ include file="../common/head.jspf" %>
<div class="articleList">
	<section class="relative m">
		<div class="absolute">
			<div>게시글 번호: ${article.id}</div>
			<div>작성일 : ${article.regDate.substring(0,10)}</div>
			<div>작성자 : ${article.loginedId}</div>
			<div>제목 : ${article.title}</div>
			<div>내용 : ${article.body}</div>
		</div>
	</section>
<%@ include file="../common/foot.jspf" %>