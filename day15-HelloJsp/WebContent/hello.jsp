<%--
	1. JSP 주석 : JSP가 Servlet으로 변환될 때 무시됨
	2. <%@ 		: Directive Tag : 현재 페이지에 대한 설정을 지정할 때 사용
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01 Hello JSP</title>
</head>
<body>
	<% 
	// 3. <%--.... : Scriptlet Tag : JSP안에서 순수 자바코드를 쓸 수 있는 태그
									//너무 많이 사용하면 가독성을 떨어뜨림
									// 이 태그 안에는 완전한 자바 [문장] 들어감 
									// 문장(statment) : ;으로 종료되는 한 줄
									// (1) 할당문	: 변수에 값 저장
									// (2) 메소드 호출문 : object.toStirng()
									// (3) 제어구조 : if, while, for
									// (4) 지역변수 선언문
									
									// 문장이 아닌 것(메소드 선언, 클래스 선언 등)은 불가능
	
%>
</body>
</html>