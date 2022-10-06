<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="JOIN"/>
<%@ include file="../common/head.jspf" %>
    <section class="flex justify-center mt-14">
    	<div>
        <h1>JOIN</h1>
        <form action="../member/dojoin?">
            <div>
                <input type="text" name="loginId" id="loginId" autocomplete="off" placeholder="LOGINID" required>
                <label for="id"> &nbsp;&nbsp;LOGINID</label>
            </div>
            <div>
                <input type="text" name="loginPw" id="loginPw" autocomplete="off" placeholder="PASSWORD" required>
                <label for="id"> &nbsp;&nbsp;PASSWORD</label>
            </div>
             <div>
                <input type="text" name="name" id="name" autocomplete="off" placeholder="NAME" required>
                <label for="id"> &nbsp;&nbsp;NAME</label>
            </div>
            <div>
                <input type="text" name="nickname" id="loginPw" autocomplete="off" placeholder="NICK NAME" required>
                <label for="id"> &nbsp;&nbsp;NICK NAME</label>
            </div>
            <div>
                <input type="tel" id="cellphoneNum" name="cellphoneNum" placeholder="01012341234" pattern="[0-9]{11}" autocomplete="off" required>
                <label for="id"> &nbsp;&nbsp;PHONE NUMBER</label>
            </div>
            <div>
                <input type="email" name="email" id="email" autocomplete="off" placeholder="E-MAIL" required>
                <label for="id"> &nbsp;&nbsp;E-MAIL</label>
            </div>
            <div>
                <button type="submit">JOIN</button>
            </div>
        </form>

        <a href="../home/main">메인페이지로 이동</a>
        </div>
    </section>
<%@ include file="../common/foot.jspf" %>