<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ADMINISTRATOR" />
<%@ include file="../common/head.jspf"%>

<div class="text-center text-3xl mt-11">
	<h1>관리자 페이지</h1>
</div>
<div class="overflow-x-auto mx-40 mt-11 text-center table-box-type-1">
	<table class=" w-full table-fixed">
		<thead class="bg-black text-white text-xl">
			<tr>
				<th class="w-14">번호</th>
				<th class="w-20">가입일자</th>
				<th>아이디</th>
				<th>이름</th>
				<th>닉네임</th>
				<th>전화번호</th>
				<th>이메일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="${memberList}">
				<tr class="hover:bg-black transition duration-300 hover:text-white">
					<th>${member.id}</th>
					<td>${member.regDate.substring(5,16)}</td>
					<td>${member.loginId}</td>
					<td>${member.name}</td>
					<td>${member.nickname}</td>
					<td>${member.cellphoneNum}</td>
					<td>${member.email}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
<%@ include file="../common/foot.jspf"%>