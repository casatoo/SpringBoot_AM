<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
body, ul, li {
	margin: 0;
	padding: 0;
	list-style: none;
}
a{
	text-decoration: none;
	color: inherit;
}
.nav-bar{
display: flex;
justify-content: space-around;
}
</style>
<title>LOGIN</title>
</head>
<body>

<nav class="nav-bar">
    <section>
    <h1><a href="../home/main">Article Manager</a></h1>
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
            <a href="">Forget password?</a>
        </div>
    </section>
</body>
</html>