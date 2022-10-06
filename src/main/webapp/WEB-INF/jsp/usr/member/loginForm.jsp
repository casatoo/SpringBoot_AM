<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="LOGIN"/>
<%@ include file="../common/head.jspf" %>
    <section class="flex justify-center mt-14">
    	<div>
        <h1>LOGIN</h1>
        <form action="../member/doLogin?">
            <div>
                <input type="text" name="loginId" id="loginId" autocomplete="off" required>
                <label for="id">USER NAME</label>
            </div>
            <div>
                <input type="password" name="loginPw" id="loginPw" autocomplete="off" required>
                <label for="id">PASSWORD</label>
            </div>
            <div>
                <button type="submit">LOGIN</button>
            </div>
        </form>

        <a href="../home/main">메인페이지로 이동</a>
        </div>
    </section>
<%@ include file="../common/foot.jspf" %>