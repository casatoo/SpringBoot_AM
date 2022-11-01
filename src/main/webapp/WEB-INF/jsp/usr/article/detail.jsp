<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE DETAIL" />
<%@ include file="../common/head.jspf"%>

<script>
	var reaction = ${reactionRd};
	const params = {};
	params.id = parseInt('${param.id}');
	const isLogined = ${rq.isLogined()};
</script>

<script>
	let CommentWrite__submitFormDone = false;
	
	function CommentWrite__submitForm(form){
		
		if(CommentWrite__submitFormDone){
			return;
		}
		form.comment.value = form.comment.value.trim();
		
		if (form.comment.value.length < 2) {
			alert('2글자 이상 입력해주세요');
			form.comment.focus();
			return;
		}
		
		CommentWrite__submitFormDone = true;
		form.submit();
	}
</script>

<script>
	function ArticleDetail__increaseHitCount() {
		const localStorageKey = 'article__' + params.id + '__alreadyView';
		if (localStorage.getItem(localStorageKey)) {
			return;
		}
		localStorage.setItem(localStorageKey, true);
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
		setTimeout(ArticleDetail__increaseHitCount, 2000);
	})
</script>

<script>
const ArticleDetail__goodReactionPoint = () =>{
	if(!isLogined){
		loginAlert();
		return;
	}
	$.get('../reaction/doReaction',{
		relId : ${article.id},
		point : 1,
		ajaxMode : 'Y'
	}, function(data){
		$('.article-detail__Reaction').empty().html(data.data1);
		$('.article-detail__goodReaction').empty().html(data.data2);
		$('.article-detail__badReaction').empty().html(data.data3);
		if(reaction == -1 || reaction == 0){
			reaction = 1;
		}else if(reaction == 1 ){
			reaction = 0;
		}
		reactionPick();
	}, 'json');
}
</script>
<script>
const ArticleDetail__badReactionPoint = () =>{
	if(!isLogined){
		loginAlert();
		return;
	}
	$.get('../reaction/doReaction',{
		relId : ${article.id},
		point : -1,
		ajaxMode : 'Y'
	}, function(data){
		$('.article-detail__Reaction').empty().html(data.data1);
		$('.article-detail__goodReaction').empty().html(data.data2);
		$('.article-detail__badReaction').empty().html(data.data3);
		if(reaction == 1 || reaction == 0){
			reaction = -1;
		}else if(reaction == -1 ){
			reaction = 0;
		}
		reactionPick();
	}, 'json');
}
</script>
<script>
	const reactionPick = () =>{
		if(reaction == 1){
			$(".goodReaction").css("color","red");
			$(".badReaction").css("color","black");
		}else if(reaction == -1){
			$(".goodReaction").css("color","black");
			$(".badReaction").css("color","red");
		}else if(reaction == 0){
			$(".goodReaction").css("color","black");
			$(".badReaction").css("color","black");
		}
	}
	$(function() {
	reactionPick();
	})
</script>

<script>
const loginAlert = () =>{
	alert("로그인해주세요");
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
														<button class="btn btn-outline goodReaction" onclick="ArticleDetail__goodReactionPoint()">
																좋아요<i class="fa-solid fa-thumbs-up"></i>&nbsp;&nbsp;<span class="article-detail__goodReaction">${article.goodReactionPoint}</span>
														</button>
														<button class="btn btn-outline badReaction" onclick="ArticleDetail__badReactionPoint()">
																싫어요<i class="fa-solid fa-thumbs-down"></i>&nbsp;&nbsp;<span class="article-detail__badReaction">${article.badReactionPoint}</span>
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

				<div class="btns mt-4 mb-4">
						<button class="btn btn-outline" onclick="location.href='../article/list?boardId=${article.boardId}&page=1';">리스트로
								돌아가기</button>
						<c:if test="${rq.loginedMemberId eq article.memberId}">
								<button class="btn btn-outline " onclick="location.href='../article/modify?id=${article.id }';">수정</button>
								<button class="btn btn-outline "
										onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false; location.href='../article/doDelete?id=${article.id }&boardId=${article.boardId}';">삭제</button>
						</c:if>
				</div>
		</div>
		<div class="mx-40 text-sm">
				<c:if test="${rq.isLogined()}">
						<form action="../comment/doAdd?" method="post" onsubmit="CommentWrite__submitForm(this); return false;">
								<input type="hidden" value="${article.id}" name="id" /> <input type="hidden" value="article" name="relTypeCode" />
								<label for="comment">댓글작성 :</label> <input type="text" placeholder="댓글" id="comment" name="comment"
										class="h-11 w-auto" required />
								<button class="h-9 comment-write-btn" type="submit">댓글 작성</button>
						</form>
				</c:if>
				<div class="overflow-x-auto mt-4 mb-4 text-center table-box-type-1">
						<table class="w-full">
								<tbody>
										<c:forEach var="comment" items="${comments}">
												<tr>
														<th class="text-left">${comment.extra__writerName}&nbsp;:&nbsp;&nbsp;${comment.comment}</th>
														<td class="w-36"><c:if test="${rq.loginedMemberId eq comment.memberId}">
																		<button class="comment-modify-btn" onclick="#">수정</button>
																		<button class="comment-delete-btn"
																				onclick="location.href='../comment/doDelete?id=${comment.id}&relId=${comment.relId}';">삭제</button>
																</c:if>
																<button class="comment-comment-btn">댓글</button></td>
												</tr>
										</c:forEach>
								</tbody>
						</table>
				</div>
		</div>

</section>
<%@ include file="../common/foot.jspf"%>