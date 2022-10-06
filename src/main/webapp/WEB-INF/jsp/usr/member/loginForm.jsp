<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="LOGIN"/>
<%@ include file="../common/head.jspf" %>
    <section class="flex justify-center mt-14">
        <div class="p-5 bg-blue-100 rounded-md">
        <h1>LOGIN</h1>
        <form action="../member/doLogin?">
            <div>
                <label for="id" class="block">ID</label>
                <input type="text" name="loginId" id="loginId" autocomplete="off"required>
            </div>
            <div>
                <label for="id" class="block">PASSWORD</label>
                <input type="password" name="loginPw" id="loginPw" autocomplete="off" required>
            </div>
            <div>
                <button type="submit" class="hover:text-blue-700 p-5">LOGIN</button>
		        <a href="../home/main" class="hover:text-blue-700 p-5">메인페이지로 이동</a>
            </div>
        </form>
	    </div>
    </section>
<%@ include file="../common/foot.jspf" %>