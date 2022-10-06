<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE LIST"/>
<%@ include file="../common/head.jspf" %>

<section class="mt-8">
<div class="container mx-auto">
<div class="table-box-list-1">
	<table class="text-center">
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
</div>
</section>
<%@ include file="../common/foot.jspf" %>