<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="JOIN"/>
<%@ include file="../common/head.jspf" %>
    <section class="flex justify-center mt-14">
      	<div class="p-5 bg-blue-100 rounded-md">
        <h1>JOIN</h1>
        <form action="../member/dojoin?">
            <div>
                <label for="id" class="block">ID</label>
                <input type="text" name="loginId" id="loginId" autocomplete="off"required>
            </div>
            <div>
                <label for="id" class="block">PASSWORD</label>
                <input type="text" name="loginPw" id="loginPw" autocomplete="off"  required>
            </div>
             <div>
                <label for="id" class="block">NAME</label>
                <input type="text" name="name" id="name" autocomplete="off" required>
            </div>
            <div>
                <label for="id" class="block">NICK NAME</label>
                <input type="text" name="nickname" id="loginPw" autocomplete="off" required>
            </div>
            <div>
                <label for="id" class="block">PHONE NUMBER</label>
                <input type="tel" id="cellphoneNum" name="cellphoneNum" placeholder="01012341234" pattern="[0-9]{11}" autocomplete="off" required>
            </div>
            <div>
                <label for="id" class="block">E-MAIL</label>
                <input type="email" name="email" id="email" autocomplete="off" required>
            </div>
            <div class="block">
                <button type="submit" class="hover:text-blue-700 p-5">JOIN</button>
		        <a href="../home/main" class="hover:text-blue-700 p-5">메인페이지로 이동</a>
            </div>
        </form>
        </div>
    </section>
<%@ include file="../common/foot.jspf" %>