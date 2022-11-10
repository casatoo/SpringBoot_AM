<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="LOGIN" />
<%@ include file="../common/head.jspf"%>
<script>
	if(${rq.isLogined()}){
		alert("이미 로그인중입니다.");
		history.back();
	}
</script>
<section class="flex justify-center mt-14">
	<div class="w-full max-w-xs">
		<form action="../member/doLogin?"
			class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
			<input type="hidden" name="afterUri" id="afterUri" value="${afterUri}"/>
			<div class="mb-4">
				<label class="block text-gray-700 text-sm font-bold mb-2"
					for="username"> Username </label> <input
					class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
					id="loginId" name="loginId" type="text" placeholder="id">
			</div>
			<div class="mb-6">
				<label class="block text-gray-700 text-sm font-bold mb-2"
					for="password"> Password </label> <input
					class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
					id="loginPw" name="loginPw" type="password" placeholder="pw">
			</div>
			<div class="flex items-center justify-between">
				<button
					class="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800"
					type="submit">로그인</button>
								<a class="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800" href="../member/join">
										회원가입 </a> <a
					class="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800"
					href="#"> 찾기 </a>
			</div>
			
		</form>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>