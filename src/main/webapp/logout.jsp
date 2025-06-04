<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	String id = (String)session.getAttribute("ID");
%>
<form action="Logout.do">
<font color="red">환영합니다~ <%= id %>님!</font>
<input type="submit" value="로그아웃">
</form>
</body>
</html>