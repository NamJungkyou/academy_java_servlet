<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 실패(session)</title>
</head>
<body>
<h3>로그인 실패하였습니다.</h3>
<a href="${pageContext.request.contextPath}/session/login">로그인</a>
<%-- <a href="<%=getServletContext().getContextPath()%>/session/login">로그인</a> --%>
</body>
</html>