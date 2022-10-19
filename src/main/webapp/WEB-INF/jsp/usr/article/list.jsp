<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${board.name}" />
<%@ include file="../common/head.jspf"%>
<div class="text-center text-3xl mt-11">
		<h1>${board.name}게시판</h1>
		<div class="text-base ">게시글 ${getTotalArticle}개</div>
</div>
<div class="overflow-x-auto mx-40 mt-11 text-center table-box-type-1">
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
										<td onClick="location.href='../article/detail?id=${article.id}'" style="cursor: pointer;">${article.title}</td>
										<td>${article.extra__writerName}</td>
								</tr>
						</c:forEach>
				</tbody>
		</table>
		<div class="flex justify-center">
				<div class="mt-4 btn-group">
						<c:set var="pageMenuLen" value="4" />
						<c:set var="startPage" value="${page-pageMenuLen >=1 ? page-pageMenuLen : 1}" />
						<c:set var="endPage" value="${page + pageMenuLen <= pageCount ? page + pageMenuLen : pageCount}" />
						<c:if test="${startPage > 1}">
								<button class="btn btn-sm" onclick="location.href='../article/list?boardId=${boardId}&page=1';">1</button>
						</c:if>
						<c:if test="${startPage > 2}">
								<button class="btn btn-sm">...</button>
						</c:if>
						<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
								<button class="btn btn-sm ${page == pageNum ? 'btn-active' : '' }"
										onclick="location.href='../article/list?boardId=${boardId}&page=${pageNum}';">${pageNum}</button>
						</c:forEach>
						<c:if test="${endPage < pageCount-1}">
								<button class="btn btn-sm">...</button>
						</c:if>
						<c:if test="${endPage < pageCount}">
								<button class="btn btn-sm" onclick="location.href='../article/list?boardId=${boardId}&page=${pageCount}';">${pageCount}</button>
						</c:if>
				</div>
		</div>
		<div class="mt-4">
				<form action="../article/list?">
						<input type="hidden" name="boardId" value="${boardId}" /> <input type="text" placeholder="searsh?" name="search"
								class="input input-bordered input-xs w-full max-w-xs" />
						<button type="submit" class="btn btn-sm">검색</button>
				</form>
		</div>
</div>
<%@ include file="../common/foot.jspf"%>