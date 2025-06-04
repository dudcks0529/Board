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
<c:set var="result" value="${ param.R }"/>
<c:choose>
	<c:when test="${result == 'OK' }">
		<script type="text/javascript">
			setTimeout(function(){alert("작업이 성공적으로 수행되었습니다.");}, 100);
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			setTimeout(function(){alert("작업이 수행되지 않았습니다. 관리자에게 문의바랍니다.");}, 100);
		</script>
	</c:otherwise>

</c:choose>
</body>
</html>