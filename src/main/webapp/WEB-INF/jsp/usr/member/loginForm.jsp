<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<link rel="stylesheet" href="/resource/common.css" />
<script src="/resource/common.js" defer="defer"></script>
</head>
<body>

<nav class="nav-bar">
    <section class="login-box">
        <h1>LOGIN</h1>
        <form action="../member/doLogin?${loginId}${loginPw}">
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
        <div>
           <a href="../home/main">메인페이지로 이동</a>
        </div>
    </section>
</body>
</html>