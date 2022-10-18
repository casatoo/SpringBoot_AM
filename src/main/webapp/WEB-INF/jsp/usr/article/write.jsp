<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="WRITE ARTICLE" />
<%@ include file="../common/head.jspf"%>
<div class="m-11">
		<form action="../article/doAdd?">
<select class="select select-bordered w-full max-w-xs block ml-12 mb-11" name="boardId" required>
  <option disabled selected >게시판 선택</option>
  <option value="1">공지사항</option>
  <option value="2">자유게시판</option>
</select>
				<label for="title">글 제목</label> <input type="text" placeholder="제목" id="title" name="title" size="30"
								class="input input-bordered input-sm w-full max-w-xs" required />
				<div class="mt-5">
						<label for="body">글 내용</label>
						<textarea class="textarea textarea-success" name="body" id="body" placeholder="내용" cols="100" rows="10" required ></textarea>
				</div>
				<button class="btn btn-outline mt-11" type="submit">글 작성</button>
		</form>
</div>
<%@ include file="../common/foot.jspf"%>