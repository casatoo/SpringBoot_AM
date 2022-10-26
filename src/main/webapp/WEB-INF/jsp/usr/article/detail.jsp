<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE DETAIL" />
<%@ include file="../common/head.jspf"%>

<script>
	const params = {};
	params.id = parseInt('${param.id}');
</script>

<script>
console.log(localStorage);
</script>

<script>
	if(localStorage.getItem(${param.id}) != ${rq.loginedMemberId}){
	function ArticleDetail__increaseHit() {
		$.get('../article/incresedHit', {
			id : params.id,
			ajaxMode : 'Y'
		}, function(data) {
			$('.article-detail__hit').empty().html(data.data1);
		}, 'json');
	}
	$(function() {
		// 실전코드
		//ArticleDetail__increaseHitCount();
		// 연습코드
		setTimeout(ArticleDetail__increaseHit, 2000);
	})
	localStorage.setItem(${param.id}, ${rq.loginedMemberId});
	}
</script>

<section class="mt-8 text-xl">
		<div class="mx-40">

				<div class="overflow-x-auto mt-11 text-center table-box-type-1">
						<table class="w-full ">
								<tbody>
										<tr>
												<th class="w-20">번호</th>
												<td>${article.id}</td>
										</tr>
										<tr>
												<th class="w-36">작성날짜</th>
												<td>${article.regDate}</td>
										</tr>
										<tr>
												<th class="w-36">수정날짜</th>
												<td>${article.updateDate}</td>
										</tr>
										<tr>
												<th class="w-20">조회수</th>
												<td><span class="article-detail__hit">${article.hit}</span></td>
										</tr>
										<tr>
												<th>추천</th>
												<td>
														<button class="btn btn-outline"
																onclick="location.href='../reaction/doReaction?relId=${article.id }&memberId=${rq.loginedMemberId}&point=1';">
																좋아요<i class="fa-solid fa-thumbs-up"></i>&nbsp;&nbsp;${article.goodReactionPoint}
														</button> 
														<button class="btn btn-outline"
																onclick="location.href='../reaction/doReaction?relId=${article.id }&memberId=${rq.loginedMemberId}&point=-1';">
																싫어요<i class="fa-solid fa-thumbs-down"></i>&nbsp;&nbsp;${article.badReactionPoint}
														</button> 
												</td>
										</tr>
										<tr>
												<th class="w-36">작성자</th>
												<td>${article.extra__writerName}</td>
										</tr>
										<tr>
												<th class="w-36">제목</th>
												<td>${article.title}</td>
										</tr>
										<tr>
												<th class="w-36 h-96">내용</th>
												<td class="text-left">${article.body}</td>
										</tr>
								</tbody>

						</table>
				</div>

				<div class="btns mt-4 ">
						<button class="btn btn-outline" onclick="location.href='../article/list?boardId=${article.boardId}&page=1';">리스트로
								돌아가기</button>
						<c:if test="${rq.loginedMemberId eq article.memberId}">
								<button class="btn btn-outline " onclick="location.href='../article/modify?id=${article.id }';">수정</button>
								<button class="btn btn-outline "
										onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false; location.href='../article/doDelete?id=${article.id }&boardId=${article.boardId}';">삭제</button>
						</c:if>
				</div>
		</div>
</section>
<%@ include file="../common/foot.jspf"%>