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

<h3 align="center">�α��� �Ǿ����ϴ�. ȯ���մϴ�~ ${ id }��~</h3>
</body>
</html>