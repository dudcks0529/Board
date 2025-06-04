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
<c:choose>
	<c:when test="${ param.R == 'OK' }">
		<script type="text/javascript">
			setTimeout(function(){alert("게시글이 수정되었습니다.");}, 100);
		</script>		
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			setTimeout(function(){alert("게시글이 수정되지 않았습니다. 관리자에게 문의해주세요.");}, 100);
		</script>
	</c:otherwise>
</c:choose>
</body>
</html>