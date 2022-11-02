<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="INFO" />
<%@ include file="../common/head.jspf"%>
<div>${member.id}</div>
<div>${member.name}</div>
<div>${member.email}</div>
<div>${member.cellphoneNum}</div>
<div>${member.nickname}</div>
<%@ include file="../common/foot.jspf"%>