<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>
<div class="text-center text-3xl mt-11">
<c:set var="boardId" value="${boardId}"/>
<c:choose>
	<c:when test="${boardId == 0}">
		<c:set var="pageTitle" value="ARTICLE LIST" />
		<h1>전체 글 목록</h1>
	</c:when>
	<c:when test="${boardId == 1}">
		<c:set var="pageTitle" value="ARTICLE NOTICE" />
		<h1>공지사항</h1>
	</c:when>
	<c:when test="${boardId == 2}">
		<c:set var="pageTitle" value="ARTICLE FREE" />
		<h1>자유게시판</h1>
	</c:when>
</c:choose>
</div>
<div class="overflow-x-auto mx-96 mt-11 text-center table-box-type-1">
  <table class=" w-full ">
    <thead class="bg-black text-white text-xl">
      <tr>
        <th class="w-20">번호</th>
        <th class="w-40">작성일시</th>
        <th class="w-64">제목</th>
        <th class="w-40">작성자</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="article" items="${articles}">
      <tr class="hover:bg-black transition duration-300 hover:text-white">
        <th>${article.id}</th>
        <td>${article.regDate.substring(5,16)}</td>
 		<td	onClick="location.href='../article/detail?id=${article.id}'" style="cursor:pointer;">
		${article.title}</td>
        <td>${article.extra__writerName}</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
	<c:if test="${rq.loginedMemberId ne 0}">
		<button class="btn btn-outline mt-11" onclick="location.href='../article/write?boardId=${boardId}';">글 작성</button>
	</c:if>
</div>
<%@ include file="../common/foot.jspf"%>