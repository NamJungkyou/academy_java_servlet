<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSPL을 사용하려면 페이지 상단에 태그 라이브러리 사용 선언이 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL (1) Core Tag : set, remove</title>
</head>
<body>
	<h4>&lt;c:set&gt;, &lt;c:remove&gt;</h4>
	<pre>
		&lt;c:set&gt; : setAttribute() 메소드와 같은 역할
						page | request | session | application 범위객체에 속성값을 추가하는 기능
						
		&lt;c:remove&gt; : removeAttribute() 메소드와 같은 역할
						   page | request | session | application 범위객체에 속성값을 삭제하는 기능
	
	</pre>
	
<%
	// attribute를 request에 추가하려면 스크립틀릿에서 setAttribute() 메소드로 추가
	
	// 1. 스크립틀릿에서 name 속성에 홍길동 값 추가
	request.setAttribute("name", "길동");
%>

<%-- 2. <c:set>태그로 속성 추가 --%>
<c:set var="surname" value="홍" scope="request"/>

<%-- 3. expression Tag, EL로 추출 --%>
<pre>
EL : 성 : \${requestScope.surname} = ${requestScope.surname}
	이름: \${name} = {name}
	
expression Tag
	 성 : <%=request.getAttribute("surname") %>
	이름: <%=request.getAttribute("name") %>
</pre>

<%-- 4. <c:remove>, removeAttribute()로 삭제 --%>
<c:remove var="name" scope="request"/>
<%
%>

</body>
</html>