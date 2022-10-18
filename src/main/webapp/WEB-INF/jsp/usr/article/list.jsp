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
		<div class="flex justify-between">
				<div class="mt-4 btn-group">
						<c:if test="${page > 1 }">
								<button class="btn btn-sm" onclick="location.href='../article/list?boardId=${boardId}&page=1';">◀◀</button>
								<button class="btn btn-sm" onclick="location.href='../article/list?boardId=${boardId}&page=${page-1}';">◀</button>
						</c:if>
								<c:forEach var="page" begin="1" end="${pageCount}">
										<button class="btn btn-sm" onclick="location.href='../article/list?boardId=${boardId}&page=${page}';">${page}</button>
								</c:forEach>
						<button class="btn btn-sm" onclick="location.href='../article/list?boardId=${boardId}&page=${page+1}';">▶</button>
						<button class="btn btn-sm" onclick="location.href='../article/list?boardId=${boardId}&page=${pageCount}';">▶▶</button>
				</div>
		</div>
</div>
<%@ include file="../common/foot.jspf"%>