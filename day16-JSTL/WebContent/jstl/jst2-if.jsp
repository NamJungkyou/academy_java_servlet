<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL (2) Core Tag : if</title>
</head>
<body>
	<h4>&lt;c:if&gt;</h4>
	<pre>
&lt;c:if&gt; : if 구문과 같이노리 결과에 따라 선택으르 분기시키는 기능
				test 속성 값이 true/false인지에 따라 분기
				test 속성 값에 EL을 사용할 수 있다.
</pre>

	<hr>
	<h4>&lt;c:if&gt;의 활용</h4>
	1. request에 name이라는 속성의 값이 있는가? EL : \${not empty requestScope.name}

	&lt;c:if&gt; dhk EL을 조합
	<c:if test="${not empty requestScope.name}">
	request에 name이 없습니다.
</c:if>
	<c:if test="${empty requestScope.name}">
	request에 name이 없습니다.
</c:if>
	<pre>
<%
	// <c:if>와 동일한 일을 하는 스크립틀릿 코드
	if (request.getAttribute("name") != null) {
%>
		request에 값이 있습니다.
<%
	} else {
%>
 	requestdp 값이 없습니다.
<%
	}
%>
</pre>

<hr>

<pre>
2. name이 '길동'입니까?
	EL : /${requestScope.name eq '길동'}
	
	<c:if test="${requestScope.name eq '길동'}">
	이름이 '길동'입니다.
	</c:if>
	<c:if test="${requestScope.name ne '길동'}">
	이름이 '길동'이 아닙니다.
	</c:if>
</pre>
</body>
</html>