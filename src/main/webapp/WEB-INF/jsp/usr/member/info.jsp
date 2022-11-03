<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="INFO" />
<%@ include file="../common/head.jspf"%>

<%-- 입력 제한사항 --%>
<script>
	let CommentWrite__submitFormDone = false;
	
	function memberInfoModify__submitForm(form){
		
		if(memberInfoModify__submitFormDone){
			return;
		}
		form.loginPw.value = form.loginPw.value.trim();
		form.nickname.value = form.nickname.value.trim();
		form.cellphoneNum.value = form.cellphoneNum.value.trim();
		form.email.value = form.email.value.trim();
		
		if (form.loginPw.value.length < 2) {
			alert('2글자 이상 입력해주세요');
			form.comment.focus();
			return;
		}
		if (form.nickname.value.length < 2) {
			alert('2글자 이상 입력해주세요');
			form.comment.focus();
			return;
		}
		if (form.loginPw.value.length < 2) {
			alert('전화번호를 입력해주세요');
			form.comment.focus();
			return;
		}
		if (form.loginPw.value.length < 2) {
			alert('이메일형식이 아닙니다.');
			form.comment.focus();
			return;
		}
		
		CommentWrite__submitFormDone = true;
		form.submit();
	}
</script>

<%-- 회원정보 수정 --%>

<script>
const memberInfoModify =()=>{
	$('#loginPw').removeAttr('disabled').removeClass('bg-gray-300');
	$('#nickname').removeAttr('disabled').removeClass('bg-gray-300');
	$('#cellphoneNum').removeAttr('disabled').removeClass('bg-gray-300');
	$('#email').removeAttr('disabled').removeClass('bg-gray-300');
	$('#modifyBtn').addClass('hidden');
	$('#doModify').removeClass('hidden');
	$('#cancleModify').removeClass('hidden');
}
</script>

<%-- 회원정보 수정 취소 --%>

<script>
const memberCancleModify =()=>{
	$('#loginPw').attr("disabled", true).addClass('bg-gray-300').val('${member.loginPw}');
	$('#nickname').attr("disabled", true).addClass('bg-gray-300').val('${member.nickname}');
	$('#cellphoneNum').attr("disabled", true).addClass('bg-gray-300').val('${member.cellphoneNum}');
	$('#email').attr("disabled", true).addClass('bg-gray-300').val('${member.email}');
	$('#modifyBtn').removeClass('hidden');
	$('#doModify').addClass('hidden');
	$('#cancleModify').addClass('hidden');
}
</script>

<section class="flex justify-center mt-14">
	<form action="../member/doModify?" class="w-full max-w-lg"
		onsubmit="memberInfoModify__submitForm(this); return false;">
		<div class="flex flex-wrap -mx-3 mb-6">
			<div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
				<label
					class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
					for="grid-first-name"> ID </label> <input disabled
					class="appearance-none block w-full bg-gray-300 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
					type="text" name="loginId" id="loginId" autocomplete="off"
					value="${member.loginId}" required>
			</div>
			<div class="w-full md:w-1/2 px-3">
				<label
					class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
					for="grid-last-name"> PASSWORD </label> <input disabled
					class="appearance-none block w-full bg-gray-300 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
					type="text" name="loginPw" id="loginPw" value="${member.loginPw}"
					autocomplete="off" required>
			</div>
		</div>
		<div class="flex flex-wrap -mx-3 mb-6">
			<div class="w-full px-3">
				<label
					class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
					for="grid-password"> NAME </label> <input
					class="appearance-none block w-full bg-gray-300 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
					type="text" name="name" id="name" autocomplete="off"
					value="${member.name}" required disabled>
				<p class="text-gray-600 text-xs italic">Make it as long and as
					crazy as you'd like</p>
			</div>
		</div>
		<div class="flex flex-wrap -mx-3 mb-6">
			<div class="w-full px-3">
				<label
					class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
					for="grid-password"> NICK NAME </label> <input
					class="appearance-none block w-full bg-gray-300 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
					type="text" name="nickname" id="nickname" autocomplete="off"
					value="${member.nickname}" required disabled>
			</div>
		</div>
		<div class="flex flex-wrap -mx-3 mb-6">
			<div class="w-full px-3">
				<label
					class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
					for="grid-password"> PHONE NUMBER </label> <input
					class="appearance-none block w-full bg-gray-300 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
					type="tel" id="cellphoneNum" name="cellphoneNum"
					value="${member.cellphoneNum}" pattern="[0-9]{11}"
					autocomplete="off" required disabled>
			</div>
		</div>
		<div class="flex flex-wrap -mx-3 mb-6">
			<div class="w-full px-3">
				<label
					class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
					for="grid-password"> E-MAIL </label> <input
					class="appearance-none block w-full bg-gray-300 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
					type="email" name="email" id="email" autocomplete="off"
					value="${member.email}" required disabled>
			</div>
		</div>
		<button
			class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline hidden"
			type="submit" id="doModify">수정 완료</button>
		<button
			class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline hidden"
			type="button" id="cancleModify" onclick="memberCancleModify()">취소</button>
		<button
			class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
			type="button" id="modifyBtn" onclick="memberInfoModify()">회원정보
			수정</button>
	</form>
</section>
<%@ include file="../common/foot.jspf"%>