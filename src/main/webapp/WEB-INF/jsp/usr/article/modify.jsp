<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MODIFY ARTICLE" />
<%@ include file="../common/head.jspf"%>
	<div class="mt-11 ml-11">
	<div class="text-4xl mb-11 ml-11">${article.id}번 글 수정</div>
	<c:set var="article" value="${article}" />
		<form action="../article/doModify?">
			<div>
				<input type="hidden" id="id" name="id" value="${article.id}"/>
			</div>
			<div>
				<label for="title">글 제목</label>
				<input type="text" id="title" name="title"  size="30" value="${article.title}"/>
			</div>
			<div class="mt-11">
				<label for="body">글 내용</label>
				<textarea name="body" id="body" cols="100" rows="10">${article.body}</textarea>
			</div>
			<button
				class="mt-11 btn btn-outline"
				type="submit">글 수정</button>
		</form>
	</div>
<%@ include file="../common/foot.jspf"%>