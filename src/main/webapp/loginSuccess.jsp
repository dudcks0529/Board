<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:set var="id" value="${ sessionScope.ID }"></c:set>

<h3 align="center">로그인 되었습니다. 환영합니다~ ${ id }님~</h3>
</body>
</html>