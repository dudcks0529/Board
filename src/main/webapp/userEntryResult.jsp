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
<c:set var="result" value="${paran.R}"/>
<c:if test="${ result == 'YES'}">
<script type="text/javascript">
	setTimeout(function(){alert("가입되었습니다~ 축하합니다~~~");} , 100);//시간 지연 함수
</script>
</c:if>
<c:if test="${ result == 'NO' }">
<script type="text/javascript">
	alert("가입되지 않았습니다. 관리자에게 문의바랍니다.");
</script>
</c:if>
</body>
</html>















