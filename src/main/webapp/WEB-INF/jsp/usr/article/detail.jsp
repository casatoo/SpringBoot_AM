<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE DETAIL"/>
<%@ include file="../common/head.jspf" %>
<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
			<table>
				<colgroup>
					<col width="200" />
				</colgroup>

				<tbody>
					<tr>
						<th>번호</th>
						<td>${article.id }</td>
					</tr>
					<tr>
						<th>작성날짜</th>
						<td>${article.regDate }</td>
					</tr>
					<tr>
						<th>수정날짜</th>
						<td>${article.updateDate }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${article.extra__writerName }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${article.title }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>${article.body }</td>
					</tr>
				</tbody>

			</table>
		</div>

		<div class="btns mt-4">
			<button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" 
					type="button" 
					onclick="location.href='../article/list';">
					리스트로 이동
			</button>
			<c:if test="${rq.loginedMemberId eq article.memberId}">
				<a class="btn-text-link hover:text-blue-700" href="../article/modify?id=${article.id }">수정</a>
				<a class="btn-text-link hover:text-blue-700" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;"
				href="../article/doDelete?id=${article.id }"
			>삭제</a>
			</c:if>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jspf" %>