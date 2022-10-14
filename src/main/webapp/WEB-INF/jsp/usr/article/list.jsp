<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE LIST" />
<%@ include file="../common/head.jspf"%>
<div class="overflow-x-auto table-box-type-1 mt-11 mx-96 mt-11">
  <table class="table w-full">
    <thead class="bg-black">
      <tr>
        <th class="w-4">번호</th>
        <th class="w-64">작성일시</th>
        <th>제목</th>
        <th class="w-64">작성자</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="article" items="${articles}">
      <tr class="hover">
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
		<button class="btn btn-outline mt-11" onclick="location.href='../article/write';">글 작성</button>
	</c:if>
</div>
<%@ include file="../common/foot.jspf"%>