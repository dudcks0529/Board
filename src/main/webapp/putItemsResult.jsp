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
			setTimeout(function(){ alert("��ǰ������ ��ϵǾ����ϴ�."); }, 100);
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			setTimeout(function(){ alert("��ǰ������ ��ϵ��� �ʾҽ��ϴ�. �����ڿ��� ���ǹٶ��ϴ�."); }, 100);
		</script>
	</c:otherwise>
</c:choose>
</body>
</html>










